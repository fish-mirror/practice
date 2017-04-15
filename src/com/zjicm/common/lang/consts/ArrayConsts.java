package com.zjicm.common.lang.consts;

/**
 * 定义空int/long/String/byte数组常量
 */
public final class ArrayConsts {
	public static final int[] EMPTY_INT = new int[0];
	public static final Integer[] EMPTY_WRAPPER_INTEGER = new Integer[0];

	public static final long[] EMPTY_LONG = new long[0];
	public static final Long[] EMPTY_WRAPPER_LONG= new Long[0];

	public static final String[] EMPTY_STRING = new String[0];

	public static final byte[] EMPTY_BYTE = new byte[0];
	public static final Byte[] EMPTY_WRAPPER_BYTE = new Byte[0];

	public static final char[] EMPTY_CHARACTER = new char[0];

	public static final char[] BASE64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
	public static final char[] RANDOM_SEEDS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
}
