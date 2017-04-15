package com.zjicm.common.lang.util;

import com.zjicm.common.lang.consts.StringConsts;
import org.apache.commons.lang.StringUtils;

/**
 * Created by yujing on 2017/4/11.
 */
public class StringUtil extends StringUtils {

    public static boolean isEmpty(String... values) {
        if (values == null || values.length == 0) {
            return true;
        }
        for (String value : values) {
            if (isEmpty(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty(String... values) {
        return !isEmpty(values);
    }

    public static String join(String... values) {
        return join(values, StringConsts.EMPTY);
    }

    public static String normalize(String value) {
        if (StringUtil.isNotEmpty(value)) {
            return value.toLowerCase().trim();
        }

        return StringConsts.EMPTY;
    }

    public static boolean contains(String source, String sub) {
        return isNotEmpty(source) && source.contains(sub);
    }

    public static boolean contains(String source, String sub, int offset) {
        return isNotEmpty(source) && source.indexOf(sub) >= offset;
    }
}
