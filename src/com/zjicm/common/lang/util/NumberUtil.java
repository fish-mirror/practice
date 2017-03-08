package com.zjicm.common.lang.util;

import com.dxy.base.consts.StringConsts;
import com.dxy.base.util.StringUtil;

import java.nio.ByteBuffer;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Random;
import java.util.regex.Pattern;

public final class NumberUtil {
    private static Random RANDOM = new Random(System.currentTimeMillis());
    private static final Pattern CLEAR_PATTERN = Pattern.compile("[^0-9]");

    private static final NumberFormat DEFAULT_FLOAT_FORMAT = NumberFormat.getInstance();

    static {
        DEFAULT_FLOAT_FORMAT.setMaximumFractionDigits(1);
        DEFAULT_FLOAT_FORMAT.setMinimumFractionDigits(0);
        DEFAULT_FLOAT_FORMAT.setGroupingUsed(false);
    }

    public static String format(double value) {
        return DEFAULT_FLOAT_FORMAT.format(value);
    }

    public static String format(double value, int fractionDigits) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(fractionDigits);
        numberFormat.setMinimumFractionDigits(fractionDigits);
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(value);
    }

    /**
     * 判断value是否在[min,max]中
     *
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static boolean between(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * 判断value是否在[min,max]中
     *
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static boolean between(long value, long min, long max) {
        return value >= min && value <= max;
    }

    /**
     * 判断value是否为数字
     *
     * @param value
     * @return
     */
    public static boolean is(String value) {
        if (value != null && value.length() > 0) {
            char[] chars = value.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (!Character.isDigit(chars[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 判断value是否不为数字
     *
     * @param value
     * @return
     */
    public static boolean not(String value) {
        return !is(value);
    }

    /**
     * 将一个Object转换成int。Number以及String形式的数字会转换成对应的int类型，否则为0
     *
     * @param value 需要转换成int的对象
     * @return
     */
    public static int parseIntQuietly(Object value) {
        return parseIntQuietly(value, 0);
    }

    /**
     * 将一个Object转换成int。Number以及String形式的数字会转换成对应的int类型，否则为设定的def
     *
     * @param value 需要转换成int的对象
     * @param def   默认值
     * @return
     */
    public static int parseIntQuietly(Object value, int def) {
        if (value != null) {
            if (value instanceof Number) {
                return ((Number) value).intValue();
            }
            try {
                return Integer.valueOf(value.toString());
            } catch (Throwable e) {
            }
        }

        return def;
    }

    public static int parseIntQuietlyAfterClear(Object value, int def) {
        if (value != null) {
            if (value instanceof Number) {
                return ((Number) value).intValue();
            }
            try {
                return Integer.valueOf(CLEAR_PATTERN.matcher(value.toString()).replaceAll(StringConsts.EMPTY));
            } catch (Throwable e) {
            }
        }

        return def;
    }


    /**
     * 将一个Object转换成long。Number以及String形式的数字会转换成对应的long类型，否则为设定的def
     *
     * @param value 需要转换成int的对象
     * @param def   默认值
     * @return
     */
    public static long parseLongQuietly(Object value, long def) {
        if (value != null) {
            if (value instanceof Number) {
                return ((Number) value).longValue();
            }
            try {
                return Long.valueOf(value.toString());
            } catch (Throwable e) {
            }
        }

        return def;
    }

    /**
     * 生成一个-231到231-1之间的int值
     *
     * @return
     */
    public static int nextInt() {
        return RANDOM.nextInt();
    }

    /**
     * 生成一个[0,n)之间的随机int值
     *
     * @param n
     * @return
     */
    public static int nextInt(int n) {
        return RANDOM.nextInt(n);
    }

    public static final int random(int length) {
        if (length > 0) {
            int sum = 0;
            int n = 1;
            int r = 0;
            for (int i = 1; i < length; i++) {
                r = com.dxy.base.util.NumberUtil.nextInt(10);
                sum += r * n;
                n = n * 10;
            }
            r = 1 + com.dxy.base.util.NumberUtil.nextInt(9);
            sum += r * n;
            return sum;
        }
        return 0;
    }

    /**
     * 获得value的第index位数字，index从1开始
     *
     * @param value
     * @param index
     * @return
     */
    public static int getIntByPosition(int value, int index) {
        if (value <= 0) {
            return 0;
        }

        if (index <= 0) {
            index = 1;
        }

        String strValue = String.valueOf(value);
        if (strValue.length() < index) {
            return 0;
        }

        return Integer.valueOf("" + strValue.charAt(strValue.length() - index));
    }

    /**
     * 设置source的第index位数字为value，index从1开始，value在[0,9]区间
     *
     * @param source
     * @param index
     * @param value
     * @return
     */
    public static int setIntByPosition(int source, int index, int value) {
        if (index <= 0) {
            index = 1;
        }

        if (value <= 9 && value >= 0) {
            StringBuilder buff = new StringBuilder(String.valueOf(source));
            if (buff.length() >= index) {
                buff.setCharAt(buff.length() - index, Integer.valueOf(value).toString().charAt(0));
            } else {
                int maxIndex = index - buff.length() - 1;
                for (int i = 0; i < maxIndex; i++) {
                    buff.insert(0, "0");
                }

                buff.insert(0, value);
            }

            return Integer.valueOf(buff.toString());
        }

        return source;
    }

    /**
     * 判断source的第index位的值和value是否相等
     *
     * @param source
     * @param index
     * @param value
     * @return
     */
    public static boolean isEQ(int source, int index, int value) {
        return getIntByPosition(source, index) == value;
    }

    /**
     * 判断source的第index位的值是否大于value
     *
     * @param source
     * @param index
     * @param value
     * @return
     */
    public static boolean isGT(int source, int index, int value) {
        return getIntByPosition(source, index) > value;
    }

    /**
     * 判断source的第index位的值是否小于value
     *
     * @param source
     * @param index
     * @param value
     * @return
     */
    public static boolean isGE(int source, int index, int value) {
        return getIntByPosition(source, index) >= value;
    }

    /**
     * 将数字集合ints转为整型数组
     *
     * @param ints
     * @return
     */
    public static int[] toArray(Collection<Integer> ints) {
        if (ints == null || ints.isEmpty()) {
            return new int[0];
        }

        int[] result = new int[ints.size()];
        int idx = 0;
        for (Integer _int : ints) {
            if (_int != null) {
                result[idx++] = _int;
            }
        }
        return result;
    }

    public static final String toHexString(Long value, int length) {
        if (length > 0) {
            if (value != null) {
                String _value = Long.toHexString(value);
                if (_value.length() < length) {
                    _value = _value + StringUtil.random(length - _value.length());
                }

                return _value.toUpperCase();
            }
            return StringUtil.random(length).toUpperCase();
        }

        return StringConsts.EMPTY;
    }

    public static byte[] toBytes(long value) {
        byte[] bytes = new byte[8];
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        buf.putLong(value);
        return bytes;
    }

    public static byte[] toBytes(int value) {
        byte[] bytes = new byte[4];
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        buf.putInt(value);
        return bytes;
    }

    public static long toLong(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getLong();
    }

    public static int toInt(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }

    public static boolean isPositive(Integer value) {
        return value != null && value > 0;
    }

    public static boolean isPositive(Long value) {
        return value != null && value > 0;
    }

    public static boolean isPositive(int value) {
        return value > 0;
    }

    public static boolean isPositive(long value) {
        return value > 0;
    }

    public static boolean isNegative(int value) {
        return value < 0;
    }

    public static boolean nullOrEqualsTo(Integer source, Integer target) {
        return source == null || source.equals(target);
    }

    public static String padding(long value, int length) {
        return padding(value, length, '0');
    }

    public static String padding(long value, int length, char padding) {
        String str = String.valueOf(value);
        int size = length - str.length();
        if (size <= 0) {
            return str;
        }

        StringBuilder buff = new StringBuilder(length);
        for (int i = 0; i < size; i++) {
            buff.append(padding);
        }
        buff.append(str);
        return buff.toString();
    }

    /**
     * hi 和lo 必须是正整数
     *
     * @param hi
     * @param lo
     * @return
     */
    public static long merge(int hi, int lo) {
        return (((long) hi) << 32) | (long) lo;
    }

    public static int hi(long value) {
        return (int) (value >> 32);
    }

    public static int lo(long value) {
        return (int) (value & -1);
    }
}
