package com.zjicm.common.lang.character;


import com.zjicm.common.lang.util.BitUtil;
import org.apache.commons.lang.StringUtils;

public final class CharacterNatures {
	public static final int NUMBER = 0;
	public static final int LETTER = 1;
	public static final int SPACE = 2;
	public static final int SYMBOLS_AND_PUNCTUATION = 3;
	public static final int BRACKETS_HEAD = 4;
	public static final int BRACKETS_TAIL = 5;
	public static final int LETTER_CONNECTOR = 6;
	public static final int NUMBER_CONNECTOR = 7;
	public static final int CJK = 8;
	public static final int CJK_CHINESE = 9;
	public static final int CJK_CHINESE_NUMBER = 10;
	public static final int CJK_NONCHINESE = 11;
	public static final int WILDCARD = 12;

	private static final int[] NATURES = new int[Character.MAX_VALUE];
	static {
		for (int i = 1; i < Character.MAX_VALUE; i++) {
			if (i >= '0' && i <= '9') {
				NATURES[i] = BitUtil.set(NATURES[i], NUMBER);
			} else if ((i >= 'a' && i <= 'z') || (i >= 'A' && i <= 'Z')) {
				NATURES[i] = BitUtil.set(NATURES[i], LETTER);
			} else if (i == '\b' || i == '\t' || i == '\n' || i == '\r' || i == ' ' || i == 160) {
				NATURES[i] = BitUtil.set(NATURES[i], SPACE);
				NATURES[i] = BitUtil.set(NATURES[i], SYMBOLS_AND_PUNCTUATION);
			}

			Character.UnicodeBlock ub = Character.UnicodeBlock.of((char) i);
			if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
				NATURES[i] = BitUtil.set(NATURES[i], CJK);
				NATURES[i] = BitUtil.set(NATURES[i], CJK_CHINESE);
			} else if (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == Character.UnicodeBlock.HANGUL_SYLLABLES || ub == Character.UnicodeBlock.HANGUL_JAMO || ub == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO || ub == Character.UnicodeBlock.HIRAGANA || ub == Character.UnicodeBlock.KATAKANA
					|| ub == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS) {
				NATURES[i] = BitUtil.set(NATURES[i], CJK);
				NATURES[i] = BitUtil.set(NATURES[i], CJK_NONCHINESE);
			}if(ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION){
				NATURES[i] = BitUtil.set(NATURES[i], CJK);
				NATURES[i] = BitUtil.set(NATURES[i], CJK_NONCHINESE);
				NATURES[i] = BitUtil.set(NATURES[i], SYMBOLS_AND_PUNCTUATION);
			}
		}
		for (char c : "！@#￥%……&×（），。；‘’“”：【】『』、《》？·～,./;':\"\\[]{}`~!@#$%^&*()_+-=|".toCharArray()) {
			NATURES[c] = BitUtil.set(NATURES[c], SYMBOLS_AND_PUNCTUATION);
		}

		for (char c : "（【『《[{(".toCharArray()) {
			NATURES[c] = BitUtil.set(NATURES[c], BRACKETS_HEAD);
		}
		for (char c : "）】』》]})".toCharArray()) {
			NATURES[c] = BitUtil.set(NATURES[c], BRACKETS_TAIL);
		}
		for (char c : "一二两三四五六七八九十零壹贰叁肆伍陆柒捌玖拾百千万亿拾佰仟萬億兆卅廿".toCharArray()) {
			NATURES[c] = BitUtil.set(NATURES[c], CJK_CHINESE_NUMBER);
		}
		
		for (char c : new char[]{'#' , '&' , '+' , '-' , '.' , '@' , '_'}) {
			NATURES[c] = BitUtil.set(NATURES[c], LETTER_CONNECTOR);
		}
		
		for (char c : new char[]{',' , '.'}) {
			NATURES[c] = BitUtil.set(NATURES[c], NUMBER_CONNECTOR);
		}
		
		for(char c:new char[]{'?','*','+'}){
			NATURES[c] = BitUtil.set(NATURES[c], WILDCARD);
		}
	}
	
	public static boolean isSymbolsPunctuation(char c){
		return is(c, SYMBOLS_AND_PUNCTUATION);
	}

	public static boolean is(char c, int type) {
		return BitUtil.is(NATURES[c], type);
	}

	public static boolean isNumber(char c) {
		return is(c, NUMBER);
	}
	
	public static boolean isSpace(char c) {
		return is(c, SPACE);
	}
	
	public static boolean isLetter(char c) {
		return is(c, LETTER);
	}
	
	public static boolean isBracketsHead(char c) {
		return is(c, BRACKETS_HEAD);
	}
	
	public static boolean isBracketsTail(char c) {
		return is(c, BRACKETS_TAIL);
	}
	
	public static boolean isCJK(char c) {
		return is(c, CJK);
	}
	
	public static boolean isChinese(char c) {
		return is(c, CJK_CHINESE);
	}
	
	public static boolean isChineseNumber(char c) {
		return is(c, CJK_CHINESE_NUMBER);
	}
	
	public static boolean isLetterConnector(char c){
		return is(c, LETTER_CONNECTOR);
	}
	
	public static boolean isNumberConnector(char c){
		return is(c, NUMBER_CONNECTOR);
	}
	
	public static boolean isWildcard(char c){
		return is(c, WILDCARD);
	}
	
	public static boolean hasChineseCharacter(String word){
		if(StringUtils.isNotEmpty(word)){
			for(char c:word.toCharArray()){
				if(isChinese(c)){
					return true;
				}
			}
		}
		return false;
	}
}
