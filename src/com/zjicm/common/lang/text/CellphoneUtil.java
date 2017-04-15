package com.zjicm.common.lang.text;

import com.zjicm.common.lang.consts.StringConsts;

import java.util.regex.Pattern;

public final class CellphoneUtil {
    private static final Pattern PATTERN = Pattern.compile(StringConsts.REGX_CELLPHONE);

    public static boolean is(String cellphone) {
        if (cellphone != null && cellphone.length() > 0 && cellphone.length() < 12) {
            return PATTERN.matcher(cellphone).matches();
        }
        return false;
    }

    public static boolean not(String cellphone) {
        return !is(cellphone);
    }

    public static void main(String[] args) {
        System.out.println(is("12346579809"));
    }
}
