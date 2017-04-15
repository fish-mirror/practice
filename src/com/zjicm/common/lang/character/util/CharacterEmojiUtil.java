package com.zjicm.common.lang.character.util;

import org.apache.commons.lang.StringUtils;

/**
 * User: yuanjq
 * Date: 16/1/13
 * Time: 下午1:32
 */
public class CharacterEmojiUtil {
    /**
     * 判断一个字符是否emoji表情字符
     *
     * @param ch 待检测的字符
     */
    public static boolean isEmoji(char ch) {
        return !((ch == 0x0) || (ch == 0x9) || (ch == 0xA) || (ch == 0xD)
                 || ((ch >= 0x20) && (ch <= 0xD7FF))
                 || ((ch >= 0xE000) && (ch <= 0xFFFD)) || ((ch >= 0x10000) && (ch <= 0x10FFFF)));
    }

    /**
     * 清除一个字符串中的emoji表情字符
     */
    public static boolean containsEmoji(String s) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        StringBuilder builder = new StringBuilder(s);
        for (int i = 0; i < builder.length(); i++) {
            if (isEmoji(builder.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 清除一个字符串中的emoji表情字符
     */
    public static String cleanEmoji(String s) {
        if (StringUtils.isEmpty(s)) {
            return s;
        }
        StringBuilder builder = new StringBuilder(s);
        StringBuilder newBuilder = new StringBuilder();
        for (int i = 0; i < builder.length(); i++) {
            if (!isEmoji(builder.charAt(i))) {
                newBuilder.append(builder.charAt(i));
            }
        }
        return newBuilder.toString().trim();
    }
}
