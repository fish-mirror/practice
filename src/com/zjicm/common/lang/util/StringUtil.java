package com.zjicm.common.lang.util;

import com.zjicm.common.lang.consts.ArrayConsts;
import com.zjicm.common.lang.consts.StringConsts;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Random;

/**
 * Created by yujing on 2017/4/11.
 */
public class StringUtil extends StringUtils {

    private static Random RANDOM = new Random(System.currentTimeMillis());

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

    public static String random(final int length, char[] options) {
        if (length > 1) {
            int maxLength = 0;
            if (ArrayUtils.isEmpty(options)) {
                options = ArrayConsts.BASE64;
                maxLength = 62;
            } else {
                maxLength = options.length;
            }
            char[] buff = new char[length];
            for (int i = 0; i < length; i++) {
                buff[i] = options[RANDOM.nextInt(maxLength)];
            }
            return new String(buff);
        }

        return StringConsts.EMPTY;
    }

    public static String random(int length) {
        return random(length, ArrayConsts.RANDOM_SEEDS);
    }

    public static String randomNumbers(int length) {
        if (length > 1) {
            char[] buff = new char[length];
            for (int i = 0; i < buff.length; i++) {
                buff[i] = (char) (48 + RANDOM.nextInt(10));
            }
            return new String(buff);
        }

        return StringConsts.EMPTY;
    }

}
