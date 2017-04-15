package com.zjicm.common.lang.character;


public class CharacterHelper {
    public static boolean isEnglishWord(char input) {
        return CharacterUtil.isLetter(input) || CharacterUtil.isNumber(input);
    }

    public static boolean isNotEnglishWord(char input) {
        return !isEnglishWord(input);
    }

    public static char regularize(char input) {
        return CharacterUtil.toLowerCase(CharacterUtil.toHalfWidth(input));
    }
}
