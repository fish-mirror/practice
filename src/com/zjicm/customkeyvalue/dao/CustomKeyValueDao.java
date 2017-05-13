package com.zjicm.customkeyvalue.dao;


import com.zjicm.common.lang.page.PageResult;
import com.zjicm.common.lang.sql.dao.BaseDAO;
import com.zjicm.customkeyvalue.domain.CustomKeyValue;

/**
 *
 * Created by yujing on 2017/5/13.
 */
public interface CustomKeyValueDao extends BaseDAO<CustomKeyValue, Integer> {


    /**
     * 根据 Key 获取对象信息
     *
     * @param key 用户ID
     * @return
     */
    CustomKeyValue getByKey(String key);

    /**
     * 根据微信 key 判断是否已有记录
     *
     * @param key 微信唯一凭证
     * @return
     */
    boolean ifExistByKey(String key);

     /**
     * 获取列表数据信息
     *
     * @param page  页数
     * @param size 每页数量
     * @return
     */
    PageResult<CustomKeyValue> page(int page, int size);
}
