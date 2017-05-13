package com.zjicm.customkeyvalue.service;


import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.JsonUtil;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.common.consts.TaskConsts;
import com.zjicm.customkeyvalue.dao.CustomKeyValueDao;
import com.zjicm.customkeyvalue.domain.CustomKeyValue;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 整体缓存和相关工具操作方法
 *
 * Created by yujing on 2017/5/13.
 */
@Service
public class CustomKeyValueService {
    private static Map<String, String> customKeyValueMap = new HashMap<>();

    @Autowired
    private CustomKeyValueDao customKeyValueDao;

    /**
     * 获取 value
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        String value = null;
        if (getCustomKeyValueMap().containsKey(key)) {
            value = getCustomKeyValueMap().get(key);
        }

        return value;
    }

    /**
     * 键值对的本地缓存数据
     */
    @Scheduled(fixedDelay = TaskConsts.DATA_CACHE_TASK_TIME)
    public void buildCustomKeyValueMap() {
        PageResult<CustomKeyValue> pageResult = customKeyValueDao.page(0, 5000);
        if (pageResult != null && CollectionUtils.isNotEmpty(pageResult.getResult())) {
            List<CustomKeyValue> customKeyValues = pageResult.getResult();
            Map<String, String> _customKeyValueMapTemp = new HashMap<>();
            for (CustomKeyValue customKeyValue : customKeyValues) {
                _customKeyValueMapTemp.put(customKeyValue.getKeyStr(), customKeyValue.getValue());
            }
            customKeyValueMap = _customKeyValueMapTemp;
        }
    }

    public static Map<String, String> getCustomKeyValueMap() {
        if (MapUtils.isEmpty(customKeyValueMap)) return new HashMap<>();
        return customKeyValueMap;
    }

    /**
     * 获取 json
     * @param customKey
     * @return
     */
    public static List getListByCustomKey(String customKey) {
        String jsonStr = getValue(customKey);
        if (StringUtils.isBlank(jsonStr)) return null;

        JsonDataHolder data = JsonUtil.toObject(JsonDataHolder.class, jsonStr);
        if (data == null) return null;
        return  (List) data.getData().get("items");
    }
    /**
     * 从CustomKey中获取对应的Json格式中的有效jsonStr
     *
     * @param customKey
     * @return
     */
    public static String getJsonStrByCustomKey(String customKey) {
        List items = getListByCustomKey(customKey);

        if (CollectionUtils.isNotEmpty(items)) {
            String itemStr = JsonUtil.toString(items);
            return itemStr;
        }
        return null;
    }

    /**
     * 将自定义键值对中按标准格式存储的 json 中的 items 转为对象列表
     *
     * @param clazz
     * @param customKey
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(Class<T> clazz, String customKey) {
        if (clazz == null || StringUtils.isBlank(customKey)) return null;
        String json = getJsonStrByCustomKey(customKey);
        return JsonUtil.toList(clazz, json);
    }
}
