package com.zjicm.customkeyvalue.enums;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by yujing on 2017/5/13.
 */
public enum CustomKey {
    short_term_can_select(1);                   // 系统是否可以开始选课

    private int value;

    public int getValue() {
        return value;
    }

    CustomKey(int v) {
        this.value = v;
    }

    /**
     * 获取 CustomKey 列表
     *
     * @return
     */
    public static List<CustomKey> getList() {
        List<CustomKey> list = new ArrayList<>();
        for (CustomKey type : CustomKey.values()) {
            list.add(type);
        }
        return list;
    }

    /**
     * 获取特定类型
     *
     * @param value 值信息
     * @return CustomKey
     */
    public static CustomKey is(int value) {
        for (CustomKey type : CustomKey.values()) {
            if (type.getValue() == value) return type;
        }
        return null;
    }

    /**
     * 获取特定类型
     *
     * @param key Key 信息
     * @return CustomKey
     */
    public static CustomKey is(String key) {
        for (CustomKey type : CustomKey.values()) {
            if (StringUtils.equals(type.name(), key)) return type;
        }
        return null;
    }
}
