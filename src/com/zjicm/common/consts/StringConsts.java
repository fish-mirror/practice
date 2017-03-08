package com.zjicm.common.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yujing on 2017/3/8.
 */
public class StringConsts {
    public static final String WHITE_SPACES = " \r\n\t　   ";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String ID = "id";
    public static final String SUCCESS = "success";
    public static final String ZERO = "0";
    public static final String BLANK = " ";
    public static final String GT = ">";
    public static final String LT = "<";
    public static final String AMP = "&";
    public static final String APOS = "\'";
    public static final String SLASH = "/";
    public static final String QUOTE = "\"";
    public static final String EMPTY = "";
    public static final String COLON = ":";
    public static final String SEMICOLON = ";";
    public static final String COMMA = ",";
    public static final String QUESTION_MARK = "?";
    public static final String CRLF = "\r\n";
    public static final String REGX_IP = "^((\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";
    public static final String REGX_PID = "([1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3})|([1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z]))";
    public static final String REGX_BLANK = "^([\\s]*)$";
    public static final String REGX_REALNAME = "^([一-龥]{2,12})$|^([a-zA-Z]+[\\s\\.]?([a-zA-Z]+[\\s\\.]?){0,4}[a-zA-Z])$";
    public static final String REGX_TELEPHONE = "^(0[0-9]{2,3}(-|)(\\d{7,8}(|-\\d{1,5}|-)))$";
    public static final String REGX_CELLPHONE = "^1[34578]\\d{9}$";
    public static final String REGX_CERTIFICATENO_13 = "^[hmt][1-3](?:1[1-5]|2[1-3]|3[1-7]|4[1-6]|5[0-4]|6[1-5])\\d{10}$";
    public static final String REGX_CERTIFICATENO_15 = "^(?:(?:[1-4]{1}(?:[1-3]0|4[1-8]))|(?:[xX]{1}(?:10|4[1-8])))(?:1[1-5]|2[1-3]|3[1-7]|4[1-6]|5[0-4]|6[1-5])\\d{4}[\\da-z]{6}$";
    public static final String REGX_EMAIL = "^[a-zA-Z0-9_\\+\\-\\.]+(\\.[a-zA-Z0-9_\\+\\-]+)*@[a-zA-Z0-9]+(\\.?[a-zA-Z0-9\\-]+)*\\.([a-zA-Z]{2,4})$";
    public static final String REGX_SPIDER = ".*(bingbot|360Spider|Google|msnbot|Rambler|Yahoo|AbachoBOT|accoona|AcioRobot|ASPSeek|CocoCrawler|Dumbot|FAST-WebCrawler|GeonaBot|Gigabot|Lycos|MSRBOT|Scooter|AltaVista|IDBot|eStyle|Scrubby|baiduspider|Sogou).*";
    public static final String REGX_URL = "[a-zA-z]+://[^\\s]+";
    public static final String REGX_WORD = "[a-zA-z0-9一-龥]";
    public static final String REGX_NONWORD = "[^a-zA-Z0-9一-龥]+";
    public static final String REGX_UUID = "(?:[0-9a-f]{8})-(?:[0-9a-f]{4})-(?:[0-9a-f]{4})-(?:[0-9a-f]{4})-(?:[0-9a-f]{12})";
    public static final String HTML_ENTITY_LT = "&lt;";
    public static final String HTML_ENTITY_GT = "&gt;";
    public static final String HTML_ENTITY_QUOTE = "&quot;";
    public static final String HTML_ENTITY_AMP = "&amp;";
    public static final String HTML_ENTITY_APOS = "&#39;";
    public static final Map<String, String> HTML_ENTITY_MAP = new HashMap();
    public static final String HTTP_METHOD_GET = "GET";
    public static final String HTTP_METHOD_PUT = "PUT";
    public static final String HTTP_METHOD_POST = "POST";
    public static final String HTTP_METHOD_DELETE = "DELETE";
    public static final String HTTP_HEADER_USER_AGENT = "User-Agent";
    public static final String HTTP_HEADER_REFERER = "referer";
    public static final String HTTP_HEADER_PRAGMA = "Pragma";
    public static final String HTTP_HEADER_EXPIRES = "Expires";
    public static final String HTTP_HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String DATE_FORMAT_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_PATTERN_HTTP = "EEE, dd-MMM-yyyy HH:mm:ss z";
    public static final String DATE_FORMAT_PATTERN_SHORT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_PATTERN_INT = "yyyyMMdd";
    public static final String DATE_FORMAT_PATTERN_MONTH_INT = "yyyyMM";
    public static final String CHARSET_GBK = "GBK";
    public static final String CHARSET_UTF8 = "UTF-8";
    public StringConsts() {
    }

    static {
        HTML_ENTITY_MAP.put("&lt;", "<");
        HTML_ENTITY_MAP.put("&gt;", ">");
        HTML_ENTITY_MAP.put("&amp;", "&");
        HTML_ENTITY_MAP.put("&quot;", "\"");
        HTML_ENTITY_MAP.put("&#39;", "\'");
        HTML_ENTITY_MAP.put("&#91;", "[");
        HTML_ENTITY_MAP.put("&#93;", "]");
        HTML_ENTITY_MAP.put("<", "&lt;");
        HTML_ENTITY_MAP.put(">", "&gt;");
        HTML_ENTITY_MAP.put("&", "&amp;");
        HTML_ENTITY_MAP.put("\"", "&quot;");
        HTML_ENTITY_MAP.put("\'", "&#39;");
    }
}
