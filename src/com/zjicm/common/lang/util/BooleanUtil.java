package com.zjicm.common.lang.util;

public final class BooleanUtil {
    public static boolean is(Boolean bool) {
        return bool != null && bool;
    }

    public static boolean not(Boolean bool) {
        return !is(bool);
    }

    public static boolean parse(Object value, boolean def) {
        if (value != null) {
            if (value instanceof Boolean) {
                return (Boolean) value;
            }

            String str = value.toString();
            if (str.equalsIgnoreCase("1") || str.equalsIgnoreCase("true") || str.equalsIgnoreCase("on") ||
                str.equalsIgnoreCase("yes")) {
                return true;
            } else if (str.equalsIgnoreCase("0") || str.equalsIgnoreCase("false") || str.equalsIgnoreCase("off") ||
                       str.equalsIgnoreCase("no")) {
                return false;
            }
        }
        return def;
    }

    public static boolean parse(Object value) {
        return parse(value, false);
    }

    public static boolean xor(boolean var1, boolean var2) {
        return var1 && !var2 || !var1 && var2;
    }
}
