package com.zjicm.common.lang.util;

import java.util.UUID;

/**
 * UUID 信息获取
 */
public final class UUIDUtil {
    /**
     * 获取随机 UUID，默认带连字符 「-」
     *
     * @return
     */
    public static String get() {
        return get(true);
    }

    /**
     * 获取随机 UUID
     *
     * @param useHyphen 是否带连字符 「-」
     * @return
     */
    public static String get(boolean useHyphen) {
        if (useHyphen) {
            return UUID.randomUUID().toString();
        }
        return UUID.randomUUID().toString().replace("-", "");
    }
}
