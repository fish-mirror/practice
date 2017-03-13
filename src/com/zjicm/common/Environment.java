package com.zjicm.common;


import java.nio.charset.Charset;

public final class Environment {
    public static final String NAME = "高校实习管理系统";


    public static final int UC_APPID = 58;
    public static final int ID = Integer.parseInt(System.getProperty("server.id", "1"));
    public static final long mask = Long.parseLong(System.getProperty("mask", String.valueOf(Long.MAX_VALUE)));

    public static final String CHARSET_NAME = "UTF-8";
    public static final Charset CHARSET = Charset.forName(CHARSET_NAME);

    public static String DOMAIN = "ayuya.me";
    public static String WEBROOT = "http://" + DOMAIN;
    public static String WEBROOT_HTTPS = "https://" + DOMAIN;
    public static final String WEBINDEX = "/";


}
