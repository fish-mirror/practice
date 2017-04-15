package com.zjicm.common.lang.http.util;

import com.zjicm.common.Server;
import com.zjicm.common.lang.character.consts.CharacterConsts;
import com.zjicm.common.lang.http.consts.HttpConsts;
import com.zjicm.common.lang.consts.StringConsts;
import com.zjicm.common.lang.http.beans.PathInfo;
import com.zjicm.common.lang.io.CloseUtil;
import com.zjicm.common.lang.json.JsonUtil;
import com.zjicm.common.lang.text.CellphoneUtil;
import com.zjicm.common.lang.util.BooleanUtil;
import com.zjicm.common.lang.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.web.method.HandlerMethod;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1. MIME信息映射Map
 * 2. 请求来源对象DataSource
 * 3. URL编码，获取ContentType各种信息，重定向操作
 * 3. 爬虫判断
 * 4. 编码转义（HTML/JSON/Map/URL）
 * 5. 获取请求的不同类型的URL
 */
public final class WebUtil {
    private static final Map<String, String> MIMES = new HashMap<String, String>();

    static {
        MIMES.put("evy", "application/envoy");
        MIMES.put("fif", "application/fractals");
        MIMES.put("spl", "application/futuresplash");
        MIMES.put("hta", "application/hta");
        MIMES.put("acx", "application/internet-property-stream");
        MIMES.put("hqx", "application/mac-binhex40");
        MIMES.put("oda", "application/oda");
        MIMES.put("axs", "application/olescript");
        MIMES.put("pdf", "application/pdf");
        MIMES.put("prf", "application/pics-rules");
        MIMES.put("p10", "application/pkcs10");
        MIMES.put("crl", "application/pkix-crl");
        MIMES.put("ai", "application/postscript");
        MIMES.put("eps", "application/postscript");
        MIMES.put("ps", "application/postscript");
        MIMES.put("rtf", "application/rtf");
        MIMES.put("setpay", "application/set-payment-initiation");
        MIMES.put("setreg", "application/set-registration-initiation");

        MIMES.put("doc", "application/msword");
        MIMES.put("dot", "application/msword");
        MIMES.put("xla", "application/vnd.ms-excel");
        MIMES.put("xlc", "application/vnd.ms-excel");
        MIMES.put("xlm", "application/vnd.ms-excel");
        MIMES.put("xls", "application/vnd.ms-excel");
        MIMES.put("xlt", "application/vnd.ms-excel");
        MIMES.put("xlw", "application/vnd.ms-excel");
        MIMES.put("msg", "application/vnd.ms-outlook");
        MIMES.put("sst", "application/vnd.ms-pkicertstore");
        MIMES.put("cat", "application/vnd.ms-pkiseccat");
        MIMES.put("stl", "application/vnd.ms-pkistl");
        MIMES.put("pot", "application/vnd.ms-powerpoint");
        MIMES.put("pps", "application/vnd.ms-powerpoint");
        MIMES.put("ppt", "application/vnd.ms-powerpoint");
        MIMES.put("mpp", "application/vnd.ms-project");
        MIMES.put("wcm", "application/vnd.ms-works");
        MIMES.put("wdb", "application/vnd.ms-works");
        MIMES.put("wks", "application/vnd.ms-works");
        MIMES.put("wps", "application/vnd.ms-works");
        MIMES.put("hlp", "application/winhlp");
        MIMES.put("bcpio", "application/x-bcpio");
        MIMES.put("cdf", "application/x-cdf");
        MIMES.put("z", "application/x-compress");
        MIMES.put("tgz", "application/x-compressed");
        MIMES.put("cpio", "application/x-cpio");
        MIMES.put("csh", "application/x-csh");
        MIMES.put("dcr", "application/x-director");
        MIMES.put("dir", "application/x-director");
        MIMES.put("dxr", "application/x-director");
        MIMES.put("dvi", "application/x-dvi");
        MIMES.put("gtar", "application/x-gtar");
        MIMES.put("gz", "application/x-gzip");
        MIMES.put("hdf", "application/x-hdf");
        MIMES.put("ins", "application/x-internet-signup");
        MIMES.put("isp", "application/x-internet-signup");
        MIMES.put("iii", "application/x-iphone");
        MIMES.put("js", "application/x-javascript");
        MIMES.put("latex", "application/x-latex");
        MIMES.put("mdb", "application/x-msaccess");
        MIMES.put("crd", "application/x-mscardfile");
        MIMES.put("clp", "application/x-msclip");
        MIMES.put("dll", "application/x-msdownload");
        MIMES.put("m13", "application/x-msmediaview");
        MIMES.put("m14", "application/x-msmediaview");
        MIMES.put("mvb", "application/x-msmediaview");
        MIMES.put("wmf", "application/x-msmetafile");
        MIMES.put("mny", "application/x-msmoney");
        MIMES.put("pub", "application/x-mspublisher");
        MIMES.put("scd", "application/x-msschedule");
        MIMES.put("trm", "application/x-msterminal");
        MIMES.put("wri", "application/x-mswrite");
        MIMES.put("cdf", "application/x-netcdf");
        MIMES.put("nc", "application/x-netcdf");
        MIMES.put("pma", "application/x-perfmon");
        MIMES.put("pmc", "application/x-perfmon");
        MIMES.put("pml", "application/x-perfmon");
        MIMES.put("pmr", "application/x-perfmon");
        MIMES.put("pmw", "application/x-perfmon");
        MIMES.put("p12", "application/x-pkcs12");
        MIMES.put("pfx", "application/x-pkcs12");
        MIMES.put("p7b", "application/x-pkcs7-certificates");
        MIMES.put("spc", "application/x-pkcs7-certificates");
        MIMES.put("p7r", "application/x-pkcs7-certreqresp");
        MIMES.put("p7c", "application/x-pkcs7-mime");
        MIMES.put("p7m", "application/x-pkcs7-mime");
        MIMES.put("p7s", "application/x-pkcs7-signature");
        MIMES.put("sh", "application/x-sh");
        MIMES.put("shar", "application/x-shar");
        MIMES.put("swf", "application/x-shockwave-flash");
        MIMES.put("sit", "application/x-stuffit");
        MIMES.put("sv4cpio", "application/x-sv4cpio");
        MIMES.put("sv4crc", "application/x-sv4crc");
        MIMES.put("tar", "application/x-tar");
        MIMES.put("tcl", "application/x-tcl");
        MIMES.put("tex", "application/x-tex");
        MIMES.put("texi", "application/x-texinfo");
        MIMES.put("texinfo", "application/x-texinfo");
        MIMES.put("roff", "application/x-troff");
        MIMES.put("t", "application/x-troff");
        MIMES.put("tr", "application/x-troff");
        MIMES.put("man", "application/x-troff-man");
        MIMES.put("me", "application/x-troff-me");
        MIMES.put("ms", "application/x-troff-ms");
        MIMES.put("ustar", "application/x-ustar");
        MIMES.put("src", "application/x-wais-source");
        MIMES.put("cer", "application/x-x509-ca-cert");
        MIMES.put("crt", "application/x-x509-ca-cert");
        MIMES.put("der", "application/x-x509-ca-cert");
        MIMES.put("pko", "application/ynd.ms-pkipko");
        MIMES.put("zip", "application/zip");
        MIMES.put("au", "audio/basic");
        MIMES.put("snd", "audio/basic");
        MIMES.put("mid", "audio/mid");
        MIMES.put("rmi", "audio/mid");
        MIMES.put("mp3", "audio/mpeg");
        MIMES.put("aif", "audio/x-aiff");
        MIMES.put("aifc", "audio/x-aiff");
        MIMES.put("aiff", "audio/x-aiff");
        MIMES.put("m3u", "audio/x-mpegurl");
        MIMES.put("ra", "audio/x-pn-realaudio");
        MIMES.put("ram", "audio/x-pn-realaudio");
        MIMES.put("wav", "audio/x-wav");

        MIMES.put("bmp", "image/bmp");
        MIMES.put("cod", "image/cis-cod");
        MIMES.put("png", "image/png");
        MIMES.put("gif", "image/gif");
        MIMES.put("ief", "image/ief");
        MIMES.put("jpe", "image/jpeg");
        MIMES.put("jpeg", "image/jpeg");
        MIMES.put("jpg", "image/jpeg");
        MIMES.put("jfif", "image/pipeg");
        MIMES.put("svg", "image/svg+xml");
        MIMES.put("tif", "image/tiff");
        MIMES.put("tiff", "image/tiff");
        MIMES.put("ras", "image/x-cmu-raster");
        MIMES.put("cmx", "image/x-cmx");
        MIMES.put("ico", "image/x-icon");
        MIMES.put("pnm", "image/x-portable-anymap");
        MIMES.put("pbm", "image/x-portable-bitmap");
        MIMES.put("pgm", "image/x-portable-graymap");
        MIMES.put("ppm", "image/x-portable-pixmap");
        MIMES.put("rgb", "image/x-rgb");
        MIMES.put("xbm", "image/x-xbitmap");
        MIMES.put("xpm", "image/x-xpixmap");
        MIMES.put("xwd", "image/x-xwindowdump");

        MIMES.put("eml", "message/rfc822");
        MIMES.put("mht", "message/rfc822");
        MIMES.put("mhtml", "message/rfc822");
        MIMES.put("nws", "message/rfc822");
        MIMES.put("css", "text/css");
        MIMES.put("323", "text/h323");
        MIMES.put("htm", "text/html");
        MIMES.put("html", "text/html");
        MIMES.put("stm", "text/html");
        MIMES.put("uls", "text/iuls");
        MIMES.put("bas", "text/plain");
        MIMES.put("c", "text/plain");
        MIMES.put("h", "text/plain");
        MIMES.put("txt", "text/plain");
        MIMES.put("rtx", "text/richtext");
        MIMES.put("sct", "text/scriptlet");
        MIMES.put("tsv", "text/tab-separated-values");
        MIMES.put("htt", "text/webviewhtml");
        MIMES.put("htc", "text/x-component");
        MIMES.put("etx", "text/x-setext");
        MIMES.put("vcf", "text/x-vcard");

        MIMES.put("mp4", "video/mp4");
        MIMES.put("mp2", "video/mpeg");
        MIMES.put("mpa", "video/mpeg");
        MIMES.put("mpe", "video/mpeg");
        MIMES.put("mpeg", "video/mpeg");
        MIMES.put("mpg", "video/mpeg");
        MIMES.put("mpv2", "video/mpeg");
        MIMES.put("mov", "video/quicktime");
        MIMES.put("qt", "video/quicktime");
        MIMES.put("lsf", "video/x-la-asf");
        MIMES.put("lsx", "video/x-la-asf");
        MIMES.put("asf", "video/x-ms-asf");
        MIMES.put("asr", "video/x-ms-asf");
        MIMES.put("asx", "video/x-ms-asf");
        MIMES.put("avi", "video/x-msvideo");
        MIMES.put("movie", "video/x-sgi-movie");

        MIMES.put("flr", "x-world/x-vrml");
        MIMES.put("vrml", "x-world/x-vrml");
        MIMES.put("wrl", "x-world/x-vrml");
        MIMES.put("wrz", "x-world/x-vrml");
        MIMES.put("xaf", "x-world/x-vrml");
        MIMES.put("xof", "x-world/x-vrml");
    }


    /**
     * URL后添加请求Parameter
     *
     * @param url
     * @param name
     * @param value
     * @return
     */
    public static String append(String url, String name, String value) {
        if (StringUtil.isNotEmpty(url) && StringUtil.isNotEmpty(name)) {
            StringBuilder buff = new StringBuilder(url);
            if (url.indexOf('?') == -1) {
                buff.append('?');
            } else {
                buff.append('&');
            }

            buff.append(name);
            buff.append('=');
            buff.append(encodeUrl(StringUtil.defaultString(value)));
            return buff.toString();
        }
        return url;
    }

    /**
     * 编码重定向后的URL
     *
     * @param url
     * @return
     */
    public static String encodeRedirectUrl(String url) {
        if (StringUtil.isNotEmpty(url)) {
            int idx1 = url.indexOf('?');
            int idx2 = url.indexOf('#');
            if (idx1 != -1 && idx1 < (url.length() - 1) && (idx2 == -1 || idx2 > idx1)) {
                url = url.substring(0, idx1) + "?" + toQueryString(parseQuery(
                        idx2 == -1 ? url.substring(idx1 + 1) : url.substring(idx1 + 1, idx2)), true) +
                      (idx2 == -1 ? StringConsts.EMPTY : url.substring(idx2));
            }
        }
        return url;
    }

    /**
     * 重定向方法
     *
     * @param response
     * @param url
     * @param encode
     * @return
     */
    public static boolean redirect(HttpServletResponse response, String url, boolean encode) {
        if (response != null && isSafeUrl(url)) {
            try {
                if (encode) {
                    url = encodeRedirectUrl(url);
                }
                response.sendRedirect(url);
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * 根据MIME的映射表，获取ContentType信息中对应的媒体内容类型
     *
     * @param ext
     * @return
     */
    public static String getContentType(String ext) {
        if (ext != null) {
            String mime = MIMES.get(ext.toLowerCase());
            if (mime != null) {
                return mime;
            }
        }
        return "application/octet-stream";
    }

    /**
     * 获取二维码URL
     *
     * @param secure
     * @param content
     * @return
     */
    public static String getQrCodeUrl(boolean secure, String content) {
        StringBuilder buff = new StringBuilder(128);
        buff.append(secure ? "https://" : "http://");
        buff.append("api.dxy.cn/qr-code/?size=10&margin=1&format=png&url=");
        buff.append(encodeUrl(content));
        return buff.toString();
    }

    /**
     * 判断是否是用https
     *
     * @param request
     * @return
     */
    public static boolean isSecure(HttpServletRequest request) {
        return "https".equalsIgnoreCase(request.getHeader("X-FORWARDED-PROTO"));
    }

    public static String getContentTypeCss() {
        return "text/css;charset=" + Server.CHARSET;
    }

    public static String getContentTypeText() {
        return "text/plain;charset=" + Server.CHARSET;
    }

    public static String getContentTypeHtml() {
        return "text/html;charset=" + Server.CHARSET;
    }

    public static String getContentTypeJson() {
        return "application/json;charset=" + Server.CHARSET;
    }

    public static String getContentTypeJavascript() {
        return "application/javascript;charset=" + Server.CHARSET;
    }

    public static String getContentTypeXml() {
        return "application/xml;charset=" + Server.CHARSET;
    }

    private static Pattern SPIDER_PATTERN = Pattern.compile(StringConsts.REGX_SPIDER, Pattern.CASE_INSENSITIVE);

    /**
     * 爬虫判断
     *
     * @param request
     * @return
     */
    public static boolean isSpider(HttpServletRequest request) {
        if (request != null) {
            return isSpider(request.getHeader(StringConsts.HTTP_HEADER_USER_AGENT));
        }

        return false;
    }

    public static boolean isSpider(String userAgent) {
        if (userAgent != null && userAgent.length() > 0) {
            return SPIDER_PATTERN.matcher(userAgent).matches();
        }
        return false;
    }

    /**
     * 提取HTML 中除去标记的字符串
     *
     * @param source
     * @return
     */
    public static final String stripHtml(String source) {
        if (source != null && source.length() > 0) {
            StringBuilder buff = new StringBuilder(source.length());
            boolean append = true;
            for (int i = 0; i < source.length(); i++) {
                char _char = source.charAt(i);
                switch (_char) {
                    case '<':
                        append = false;
                        continue;
                    case '>':
                        append = true;
                        continue;
                    default:
                        if (append) {
                            buff.append(_char);
                        }
                }
            }

            return buff.toString();
        }
        return source;
    }

    /**
     * 对Map中的值进行转义
     *
     * @param map
     */
    public static void escapeMap(Map<String, Object> map) {
        if (MapUtils.isNotEmpty(map)) {
            for (Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value != null && value instanceof String) {
                    entry.setValue(escapeHtml(unescapeHtml((String) value)));
                }
            }
        }
    }

    /**
     * 转义JSON
     *
     * @param json
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static String escapeJson(String json) {
        if (StringUtil.isNotEmpty(json)) {
            json = json.trim();
            if (json.startsWith("[") && json.endsWith("]")) {
                Collection<Map> items = JsonUtil.toList(Map.class, json);
                if (CollectionUtils.isNotEmpty(items)) {
                    for (Map map : items) {
                        escapeMap(map);
                    }
                    return JsonUtil.toString(items);
                }
            } else {
                Map<String, Object> map = JsonUtil.toMap(json);
                if (map != null) {
                    escapeMap(map);
                    return JsonUtil.toString(map);
                }
            }
        }
        return json;
    }

    /**
     * 转义HTML 的工具方法
     *
     * @param source 需要转义的源
     * @param url 是否是url，用于区分是否转义&
     * @return
     */
    private static String escapeHtml(String source, boolean url) {
        if (source == null || source.length() == 0) {
            return source;
        }
        StringBuilder buff = new StringBuilder((int) (source.length() * 1.3));
        for (int i = 0; i < source.length(); i++) {
            char _char = source.charAt(i);
            switch (_char) {
                case CharacterConsts.LT:
                    buff.append(StringConsts.HTML_ENTITY_LT);
                    continue;
                case CharacterConsts.GT:
                    buff.append(StringConsts.HTML_ENTITY_GT);
                    continue;
                case CharacterConsts.AMP:
                    if (url) {
                        buff.append(_char);
                    } else {
                        buff.append(StringConsts.HTML_ENTITY_AMP);
                    }
                    continue;
                case CharacterConsts.QUOTE:
                    buff.append(StringConsts.HTML_ENTITY_QUOTE);
                    continue;
                case CharacterConsts.APOS:
                    buff.append(StringConsts.HTML_ENTITY_APOS);
                    continue;
                default:
                    buff.append(_char);
            }
        }

        return buff.toString();
    }

    /**
     * 转义url
     *
     * @param source
     * @return
     */
    public static String escapeUrl(String source) {
        return escapeHtml(source, true);
    }

    /**
     * 转义HTML
     *
     * @param source
     * @return
     */
    public static String escapeHtml(String source) {
        return escapeHtml(source, false);
    }

    /**
     * 反转义HTML
     *
     * @param source
     * @return
     */
    public static final String unescapeHtml(String source) {
        if (source == null || source.length() == 0) {
            return source;
        }
        StringBuilder buff = new StringBuilder(source.length());
        int i, j, skip = 0;
        boolean _continue;
        do {
            _continue = false;
            i = source.indexOf(StringConsts.AMP, skip);
            if (i > -1) {
                j = source.indexOf(StringConsts.SEMICOLON, i);
                if (j > i) {
                    String entity = StringConsts.HTML_ENTITY_MAP.get(source.substring(i, j + 1));
                    if (entity != null) {
                        buff.append(source.substring(skip, i));
                        buff.append(entity);
                    } else {
                        buff.append(source.substring(skip, j + 1));
                    }
                    skip = j + 1;
                    _continue = true;
                    continue;
                }
            }

            buff.append(source.substring(skip));
        } while (_continue);
        return buff.toString();
    }

    /**
     * 编码URL
     *
     * @param url
     * @return
     */
    public static String encodeUrl(String url) {
        if (url != null && url.length() > 0) {
            try {
                return URLEncoder.encode(url, Server.CHARSET);
            } catch (Throwable e) {
            }
        }

        return url;
    }

    /**
     * 解码URL
     *
     * @param url
     * @return
     */
    public static String decodeUrl(String url) {
        if (url != null && url.length() > 0) {
            try {
                return URLDecoder.decode(url, Server.CHARSET);
            } catch (Throwable e) {
            }
        }

        return url;
    }

    public static String getFullRequestUrl(HttpServletRequest request) {
        StringBuffer buff = request.getRequestURL();
        String query = request.getQueryString();
        if (query != null && query.length() > 0) {
            buff.append(StringConsts.QUESTION_MARK);
            buff.append(escapeHtml(unescapeHtml(query)));
        }

        return buff.toString();
    }

    public static String getFullRequestUrl(String domain, HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(domain);
        String path = request.getRequestURI();
        if (StringUtil.isNotEmpty(path)) {
            builder.append(path);
        }
        String query = request.getQueryString();
        if (query != null && query.length() > 0) {
            builder.append(StringConsts.QUESTION_MARK);
            builder.append(escapeHtml(unescapeHtml(query)));
        }
        return builder.toString();
    }


    public static String getRawFullRequestUrl(HttpServletRequest request) {
        StringBuffer buff = request.getRequestURL();
        String query = request.getQueryString();
        if (query != null && query.length() > 0) {
            buff.append(StringConsts.QUESTION_MARK);
            buff.append(query);
        }
        return buff.toString();
    }

    public static String getRawFullRequestUri(HttpServletRequest request) {
        StringBuilder buff = new StringBuilder(32);
        buff.append(request.getRequestURI());
        String query = request.getQueryString();
        if (query != null && query.length() > 0) {
            buff.append(StringConsts.QUESTION_MARK);
            buff.append(query);
        }
        return buff.toString();
    }

    public static String getFullRequestUri(HttpServletRequest request) {
        StringBuilder buff = new StringBuilder(32);
        buff.append(request.getRequestURI());
        String query = request.getQueryString();
        if (query != null && query.length() > 0) {
            buff.append(StringConsts.QUESTION_MARK);
            buff.append(escapeUrl(unescapeHtml(query)));
        }
        return buff.toString();
    }

    public static String getFullRequestUriForEcho(HttpServletRequest request, Predicate<String> predicate) {
        StringBuilder buff = new StringBuilder(128);
        buff.append(request.getRequestURI());
        final int initLength = buff.length();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            if (predicate == null || predicate.test(name)) {
                String[] values = request.getParameterValues(name);
                if (ArrayUtils.isNotEmpty(values)) {
                    for (String value : values) {
                        buff.append(buff.length() > initLength ? '&' : '?');
                        buff.append(encodeUrl(name));
                        buff.append('=');
                        buff.append(encodeUrl(StringUtil.defaultString(value)));
                    }
                }
            }
        }

        return buff.toString();
    }

    /** 设置Content-Disposition，提示用户保存服务器发送的文件
     * =
     * @param request
     * @param response
     * @param filename
     * @param attachment
     */
    public static void setHeaderContentDisposition(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   String filename,
                                                   boolean attachment) {
        if (attachment) {
            response.setHeader("Content-disposition", "attachment;" + buildContentDisposition(request, filename));
        } else {
            response.setHeader("Content-disposition", buildContentDisposition(request, filename));
        }
    }

    private static String buildContentDisposition(HttpServletRequest request, String filename) {
        String value = null;
        try {
            if (filename == null || filename.length() == 0) {
                filename = "download";
            }
            String userAgent = getUserAgent(request);
            String encodedName = encodeUrl(filename);
            if (userAgent != null) {
                userAgent = StringUtil.lowerCase(userAgent.trim());
                if (userAgent.contains("msie")) {
                    value = StringUtil.join("filename=\"", encodedName, StringConsts.QUOTE);
                } else if (userAgent.contains("opera") || userAgent.contains("mozilla")) {
                    value = StringUtil.join("filename*=", Server.CHARSET, "''", encodedName);
                } else if (userAgent.contains("safari")) {
                    value = StringUtil.join("filename=\"",
                                               new String(filename.getBytes(Server.CHARSET), "ISO8859-1"),
                                               StringConsts.QUOTE);
                } else if (userAgent.contains("applewebkit")) {
                    value = StringUtil.join("filename=\"",
                                               MimeUtility.encodeText(filename, Server.CHARSET, "B"),
                                               StringConsts.QUOTE);
                }
            }

            if (value == null) {
                value = StringUtil.join("filename=\"", encodedName, StringConsts.QUOTE);
            }
        } catch (Throwable e) {
        }

        return StringUtil.defaultString(value);
    }

    public static String getUserAgent(HttpServletRequest request) {
        if (request != null) {
            return request.getHeader(StringConsts.HTTP_HEADER_USER_AGENT);
        }
        return "";
    }

    public static String getRestMethod(HttpServletRequest request) {
        if (request == null) {
            return StringConsts.HTTP_METHOD_GET;
        }

        String method = getString(request, "_method", request.getMethod());
        return method == null || method.length() == 0 ? StringConsts.HTTP_METHOD_GET : method.toUpperCase();
    }

    public static String formatDate(Date date) {
        if (date != null) {
            DateFormat httpDateFormat = new SimpleDateFormat(StringConsts.DATE_FORMAT_PATTERN_HTTP, Locale.US);
            httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return httpDateFormat.format(date);
        }
        return StringConsts.EMPTY;
    }

    public static String extractEncoding(String contentType) {
        if (contentType != null && contentType.length() > 0) {
            contentType = StringUtil.normalize(contentType);
            int start = contentType.indexOf("charset=");
            if (start != -1) {
                start += 8;
                if (start < contentType.length() - 1) {
                    int end = contentType.indexOf(StringConsts.AMP, start);
                    String encoding = contentType.substring(start, end == -1 ? contentType.length() : end);
                    encoding = encoding.trim();
                    if ((encoding.length() > 2) && (encoding.startsWith(StringConsts.QUOTE)) &&
                        (encoding.endsWith(StringConsts.QUOTE))) {
                        encoding = encoding.substring(1, encoding.length() - 1);
                    }
                    return encoding.trim().toUpperCase();
                }
            }
        }

        return Server.CHARSET;
    }

    private static String getRawRemoteAddress(HttpServletRequest request) {
        //TODO 删除 HTTP_X_FORWARDED_FOR
        String ips = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (!StringUtil.contains(ips, ".", 2)) {
            ips = request.getHeader("X-Forwarded-For");
            if (!StringUtil.contains(ips, ".", 2)) {
                ips = request.getRemoteAddr();
            }
        }
        return ips;
    }

    public static boolean isUrlAndNotLocal(String url) {
        if (StringUtil.isNotEmpty(url)) {
            try {
                URL u = new URL(url);
                String host = u.getHost();
                if (StringUtil.isNotEmpty(host)) {
                    if (isLocalAddress(host)) {
                        return false;
                    }
                    return true;
                }

            } catch (Throwable e) {
            }
        }
        return false;
    }

    public static boolean isLocalUrl(String url) {
        if (StringUtil.isNotEmpty(url)) {
            try {
                URL u = new URL(url);
                String host = u.getHost();
                if (StringUtil.isNotEmpty(host)) {
                    if (isLocalAddress(host)) {
                        return true;
                    }
                    return false;
                }

            } catch (Throwable e) {
            }
        }
        return false;
    }

    public static boolean isLocalAddress(String host) {
        if (host.startsWith("192.168.")) {
            return true;
        }

        if (host.startsWith("10.")) {
            return true;
        }

        if (host.endsWith(".host.dxy")) {
            return true;
        }

        return "127.0.0.1".equals(host) || "localhost".equals(host);
    }

    public static String getRemoteAddressAll(HttpServletRequest request, boolean removeBlanks) {
        String ips = getRawRemoteAddress(request);
        if (StringUtil.isNotEmpty(ips)) {
            if (removeBlanks) {
                StringBuilder buff = new StringBuilder(ips.length());
                for (char c : ips.toCharArray()) {
                    if ((c >= '0' && c <= '9') || c == '.' || c == ',') {
                        buff.append(c);
                    }
                }
                return buff.toString();
            }
            return ips;
        }
        return StringConsts.EMPTY;
    }

    public static String getRemoteAddress(HttpServletRequest request) {
        String[] ips = getRemoteAddressArray(request);
        if (ips != null && ips.length > 0) {
            for (String ip : ips) {
                if (ip != null && ip.length() > 0) {
                    return ip;
                }
            }
        }

        return StringConsts.EMPTY;
    }

    public static String[] getRemoteAddressArray(HttpServletRequest request) {
        return StringUtil.split(getRemoteAddressAll(request, true));
    }

    public static String getIntranetEscapedRemoteSingleAddress(HttpServletRequest request) {
        String raw = getRawRemoteAddress(request);
        if (StringUtil.isNotEmpty(raw)) {
            String[] ips = raw.split("[,]");
            if (ArrayUtils.isNotEmpty(ips)) {
                String result = null;
                for (int i = 0; i < ips.length; i++) {
                    String ip = ips[i];
                    if (ip != null) {
                        ip = ip.trim();
                        if (Server.DEV || !isLocalAddress(ip)) {
                            result = ip;
                        }
                    }
                }
                return result;
            }
        }
        return StringConsts.EMPTY;
    }

    public static String getRemoteAddressStr(SocketAddress socketAddress) {
        if (socketAddress != null) {
            if (socketAddress instanceof InetSocketAddress) {
                return ((InetSocketAddress) socketAddress).getAddress().getHostAddress() + ":" +
                       ((InetSocketAddress) socketAddress).getPort();
            }

            return socketAddress.toString();
        }
        return StringConsts.EMPTY;
    }

    public static void setCacheHeader(HttpServletResponse response, int cacheTimeInSeconds) {
        if (cacheTimeInSeconds > 0) {
            response.setHeader("Cache-Control", "max-age=" + cacheTimeInSeconds);
            response.setDateHeader("Expires", System.currentTimeMillis() + cacheTimeInSeconds * 1000l);
        } else {
            response.setHeader("Cache-Control", "no-cache,no-store");//HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.1
            response.setDateHeader("Expires", -1);
        }
    }

    public static void write(HttpServletResponse response, String text, int cacheTimeInSeconds, String contentType) {
        response.setContentType(StringUtil.defaultIfEmpty(contentType, "text/html; charset=UTF-8"));
        setCacheHeader(response, cacheTimeInSeconds);
        try {
            response.getWriter().write(StringUtil.defaultString(text));
            response.getWriter().flush();
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void writeHtml(HttpServletResponse response, String text) {
        write(response, text, 0, getContentTypeHtml());
    }

    public static void writeXml(HttpServletResponse response, String text) {
        write(response, text, 0, getContentTypeXml());
    }

    public static void writeJson(HttpServletResponse response, String text) {
        write(response, text, 0, getContentTypeJson());
    }

    public static void writeJson(HttpServletResponse response, String text, int cacheTimeInSeconds) {
        write(response, text, cacheTimeInSeconds, getContentTypeJson());
    }

    public static void writeJavascript(HttpServletResponse response, String text) {
        write(response, text, 0, getContentTypeJavascript());
    }

    public static void writeJavascript(HttpServletResponse response, String text, int cacheTimeInSeconds) {
        write(response, text, cacheTimeInSeconds, getContentTypeJavascript());
    }

    public static void writeText(HttpServletResponse response, String text) {
        write(response, text, 0, getContentTypeText());
    }

    public static void transferTo(File source, HttpServletResponse response) throws IOException {
        if (response != null && source != null) {
            FileChannel sourceChannel = null;
            WritableByteChannel targetChannel = null;
            try {
                sourceChannel = new FileInputStream(source).getChannel();
                response.setContentLength((int) sourceChannel.size());
                targetChannel = Channels.newChannel(response.getOutputStream());
                sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
            } finally {
                CloseUtil.closeQuiet(sourceChannel);
                CloseUtil.closeQuiet(targetChannel);
            }
        }
    }

    public static void transferTo(byte[] data, HttpServletResponse response) throws IOException {
        if (response != null && data != null) {
            response.setContentLength(data.length);
            int offset = 0;
            while (offset < data.length) {
                response.getOutputStream().write(data, offset, Math.min(4096, data.length - offset));
                offset += 4096;
            }
            response.getOutputStream().flush();
        }
    }

    public static String readToString(HttpServletRequest request) {
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            String str = null;
            StringBuilder buff = new StringBuilder(request.getContentLength());
            while ((str = reader.readLine()) != null) {
                buff.append(str);
            }
            return buff.toString();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuiet(reader);
        }
        return null;
    }

    public static String toString(Date date) {
        return new SimpleDateFormat(StringConsts.DATE_FORMAT_PATTERN_HTTP, Locale.US).format(
                date == null ? new Date() : date);
    }

    public static Map<String, String> parseQuery(String query) {
        Map<String, String> params = new LinkedHashMap<String, String>();
        if (StringUtil.isNotEmpty(query)) {
            String[] segments = query.split("[&]");
            if (segments != null && segments.length > 0) {
                for (String segment : segments) {
                    if (StringUtil.isNotEmpty(segment)) {
                        String[] data = segment.split("[=]");
                        if (data != null && data.length == 2) {
                            params.put(data[0], decodeUrl(StringUtil.defaultString(data[1])));
                        }
                    }
                }
            }
        }
        return params;
    }

    public static String toQueryString(Map<String, String> params) {
        return toQueryString(params, false);
    }

    public static String toQueryString(Map<String, String> params, boolean encode) {
        if (params != null && params.size() > 0) {
            StringBuilder buff = new StringBuilder();
            for (Entry<String, String> param : params.entrySet()) {
                if (StringUtil.isNotEmpty(param.getKey(), param.getValue())) {
                    if (buff.length() > 0) {
                        buff.append("&");
                    }

                    buff.append(param.getKey());
                    buff.append("=");
                    if (encode) {
                        buff.append(encodeUrl(param.getValue()));
                    } else {
                        buff.append(param.getValue());
                    }
                }
            }
            return buff.toString();
        }

        return StringConsts.EMPTY;
    }

    public static URL toURL(String url) {
        if (StringUtil.isNotEmpty(url)) {
            try {
                return new URL(url);
            } catch (Throwable e) {
            }
        }
        return null;
    }

    public static boolean checkCsrf(HttpServletRequest request, String... suffixes) {
        if (request != null && ArrayUtils.isNotEmpty(suffixes)) {
            if (HttpConsts.GET.equals(getRestMethod(request))) {
                return true;
            }

            URL u = toURL(getReferer(request));
            if (u != null) {
                String host = u.getHost().toLowerCase();
                for (String suffix : suffixes) {
                    if (host.endsWith(suffix)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static boolean isHostSuffix(String url, String suffix) {
        if (StringUtil.isNotEmpty(url, suffix)) {
            URL u = toURL(url);
            if (u != null) {
                return u.getHost().endsWith(suffix);
            }
        }
        return false;
    }

    public static boolean isHost(String url, String host) {
        if (StringUtil.isNotEmpty(url, host)) {
            URL u = toURL(url);
            if (u != null) {
                return host.toLowerCase().equals(u.getHost());
            }
        }
        return false;
    }

    public static PathInfo getPathInfo(HttpServletRequest request) {
        if (request != null) {
            String pathInfo = request.getPathInfo();
            if (StringUtil.isNotEmpty(pathInfo)) {
                if (pathInfo.startsWith("/")) {
                    return new PathInfo(pathInfo.substring(1));
                }

                return new PathInfo(pathInfo);
            }
        }

        return PathInfo.DEFAULT;
    }

    private static Set<String> CELLPHONE_INFO_HEADERS = new HashSet<String>();

    static {
        CELLPHONE_INFO_HEADERS.add("x-up-calling-line-id");// GPRS
        CELLPHONE_INFO_HEADERS.add("x_up_calling_line_id");// GPRS
        CELLPHONE_INFO_HEADERS.add("http_x-up-calling-line-id");// GPRS
        CELLPHONE_INFO_HEADERS.add("http_x_up_calling_line_id");// GPRS
        CELLPHONE_INFO_HEADERS.add("x-up-subno");// CDMA
        CELLPHONE_INFO_HEADERS.add("http_x_up_subno");// CDMA
        CELLPHONE_INFO_HEADERS.add("x-network-info");
        CELLPHONE_INFO_HEADERS.add("deviceid");// GPRS
        CELLPHONE_INFO_HEADERS.add("http_x_up_bear_type");// GPRS
    }

    @SuppressWarnings("unchecked")
    public static String getCellphone(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String name = (String) headers.nextElement();
            if (StringUtil.isNotEmpty(name) && CELLPHONE_INFO_HEADERS.contains(name.toLowerCase())) {
                String value = request.getHeader(name);
                if (StringUtil.isNotEmpty(value)) {
                    if (value.startsWith("86")) {
                        value = value.substring(2);
                    } else if (value.startsWith("+86")) {
                        value = value.substring(3);
                    }

                    if (CellphoneUtil.is(value)) {
                        return value;
                    }
                }
            }
        }
        return null;
    }

    public static String mailto(String email, String subject, String body) {
        StringBuilder buff = new StringBuilder();
        buff.append("mailto:");
        if (StringUtil.isNotEmpty(email)) {
            buff.append(email);
        }

        boolean qm = true;
        if (StringUtil.isNotEmpty(subject)) {
            buff.append("?");
            qm = false;
            buff.append("subject=");
            buff.append(encodeUrl(subject));
        }

        if (StringUtil.isNotEmpty(body)) {
            if (qm) {
                buff.append("?");
            } else {
                buff.append("&");
            }
            buff.append("body=");
            buff.append(encodeUrl(body));
        }

        return buff.toString();
    }

    public static boolean isUrl(String url) {
        if (StringUtil.isNotEmpty(url)) {
            try {
                new URL(url);
                return true;
            } catch (Throwable e) {
            }
        }
        return false;
    }


    private static Pattern WHITE_DOMAIN_PATTERN = null;

    static {
        StringBuilder buff = new StringBuilder();
        for (String domain : new String[]{"dxy\\.(cn|com|net)",
                                          "biomart\\.cn",
                                          "dxyer\\.(cn|com)",
                                          "jobmd\\.cn",
                                          "pubmed\\.cn",
                                          "dxycdn\\.com"}) {
            if (buff.length() > 0) {
                buff.append("|");
            }
            buff.append("(^([\\w-]+\\.)*");
            buff.append(domain);
            buff.append("$)");
        }
        WHITE_DOMAIN_PATTERN = Pattern.compile(buff.toString(), Pattern.CASE_INSENSITIVE);
    }

    public static boolean isSafeUrl(String url) {
        if (Server.DEV) {
            return true;
        }
        if (StringUtil.isNotEmpty(url)) {
            try {
                URL u = new URL(url);
                if (StringUtil.isNotEmpty(u.getHost())) {
                    return WHITE_DOMAIN_PATTERN.matcher(u.getHost()).matches();
                }
            } catch (Throwable e) {
            }
        }

        return false;
    }

    public static String unsafeUrlToIndex(String url) {
        if (StringUtil.isNotEmpty(url)) {
            return isSafeUrl(url) ? url : "/";
        }

        return url;
    }

    public static String notSafeToDefault(String url, String def) {
        if (isSafeUrl(url)) {
            return url;
        }

        return def;
    }

    private static final String[] KNOWN_MOBILE_USER_AGENT_PREFIXES = new String[]{"w3c ",
                                                                                  "w3c-",
                                                                                  "acs-",
                                                                                  "alav",
                                                                                  "alca",
                                                                                  "amoi",
                                                                                  "audi",
                                                                                  "avan",
                                                                                  "benq",
                                                                                  "bird",
                                                                                  "blac",
                                                                                  "blaz",
                                                                                  "brew",
                                                                                  "cell",
                                                                                  "cldc",
                                                                                  "cmd-",
                                                                                  "dang",
                                                                                  "doco",
                                                                                  "eric",
                                                                                  "hipt",
                                                                                  "htc_",
                                                                                  "inno",
                                                                                  "ipaq",
                                                                                  "ipod",
                                                                                  "jigs",
                                                                                  "kddi",
                                                                                  "keji",
                                                                                  "leno",
                                                                                  "lg-c",
                                                                                  "lg-d",
                                                                                  "lg-g",
                                                                                  "lge-",
                                                                                  "lg/u",
                                                                                  "maui",
                                                                                  "maxo",
                                                                                  "midp",
                                                                                  "mits",
                                                                                  "mmef",
                                                                                  "mobi",
                                                                                  "mot-",
                                                                                  "moto",
                                                                                  "mwbp",
                                                                                  "nec-",
                                                                                  "newt",
                                                                                  "noki",
                                                                                  "palm",
                                                                                  "pana",
                                                                                  "pant",
                                                                                  "phil",
                                                                                  "play",
                                                                                  "port",
                                                                                  "prox",
                                                                                  "qwap",
                                                                                  "sage",
                                                                                  "sams",
                                                                                  "sany",
                                                                                  "sch-",
                                                                                  "sec-",
                                                                                  "send",
                                                                                  "seri",
                                                                                  "sgh-",
                                                                                  "shar",
                                                                                  "sie-",
                                                                                  "siem",
                                                                                  "smal",
                                                                                  "smar",
                                                                                  "sony",
                                                                                  "sph-",
                                                                                  "symb",
                                                                                  "t-mo",
                                                                                  "teli",
                                                                                  "tim-",
                                                                                  "tosh",
                                                                                  "tsm-",
                                                                                  "upg1",
                                                                                  "upsi",
                                                                                  "vk-v",
                                                                                  "voda",
                                                                                  "wap-",
                                                                                  "wapa",
                                                                                  "wapi",
                                                                                  "wapp",
                                                                                  "wapr",
                                                                                  "webc",
                                                                                  "winw",
                                                                                  "winw",
                                                                                  "xda ",
                                                                                  "xda-"};

    static {
        Arrays.sort(KNOWN_MOBILE_USER_AGENT_PREFIXES);
    }

    private static final String[] KNOWN_MOBILE_USER_AGENT_KEYWORDS = new String[]{"blackberry",
                                                                                  "webos",
                                                                                  "ipod",
                                                                                  "lge vx",
                                                                                  "midp",
                                                                                  "maemo",
                                                                                  "mmp",
                                                                                  "mobile",
                                                                                  "netfront",
                                                                                  "hiptop",
                                                                                  "nintendo DS",
                                                                                  "novarra",
                                                                                  "openweb",
                                                                                  "opera mobi",
                                                                                  "opera mini",
                                                                                  "palm",
                                                                                  "psp",
                                                                                  "phone",
                                                                                  "smartphone",
                                                                                  "symbian",
                                                                                  "up.browser",
                                                                                  "up.link",
                                                                                  "wap",
                                                                                  "windows ce"};

    private static final String[] KNOWN_TABLET_USER_AGENT_KEYWORDS = new String[]{"ipad",
                                                                                  "playbook",
                                                                                  "hp-tablet",
                                                                                  "kindle"};



    public static interface HttpServletRequestParameterHandler {
        public boolean test(String name);

        public void consume(String name, String value);
    }

    public static void iterate(HttpServletRequest request, HttpServletRequestParameterHandler handler) {
        @SuppressWarnings("unchecked")
        Enumeration<String> enums = request.getParameterNames();
        if (enums != null) {
            while (enums.hasMoreElements()) {
                String name = enums.nextElement();
                if (handler.test(name)) {
                    handler.consume(name, getRawString(request, name));
                }
            }
        }
    }

    public static void iterateOrdered(HttpServletRequest request, HttpServletRequestParameterHandler handler) {
        Set<String> names = new TreeSet<String>();
        @SuppressWarnings("unchecked")
        Enumeration<String> enums = request.getParameterNames();
        if (enums != null) {
            while (enums.hasMoreElements()) {
                String name = enums.nextElement();
                if (handler.test(name)) {
                    names.add(name);
                }
            }
        }

        for (String name : names) {
            handler.consume(name, getRawString(request, name));
        }
    }


    /**
     * @param request
     * @param name
     * @return
     */
    public static String getRawString(HttpServletRequest request, String name) {
        if (request != null && name != null) {
            return request.getParameter(name);
        }

        return null;
    }

    /**
     * @param request
     * @param name
     * @return
     */
    public static String[] getRawStrings(HttpServletRequest request, String name) {
        if (request != null && name != null) {
            return request.getParameterValues(name);
        }

        return null;
    }

    /**
     * @param request
     * @param name
     * @return
     */
    public static String[] getStrings(HttpServletRequest request, String name) {
        String[] values = getRawStrings(request, name);
        if (ArrayUtils.isNotEmpty(values)) {
            for (int i = 0; i < values.length; i++) {
                values[i] = escapeHtml(unescapeHtml(values[i]));
            }
            return values;
        }
        return null;
    }

    public static Collection<Map<String, String>> getListParams(HttpServletRequest request) {
        return getListParams(request, null);
    }

    @SuppressWarnings("unchecked")
    public static Collection<Map<String, String>> getListParams(HttpServletRequest request,
                                                                Function<String, String> func) {
        Enumeration<String> names = request.getParameterNames();
        if (names != null) {
            TreeMap<Integer, Map<String, String>> items = new TreeMap<Integer, Map<String, String>>();
            while (names.hasMoreElements()) {
                final String name = names.nextElement();
                if (StringUtil.isNotEmpty(name)) {
                    if (name.endsWith("]")) {
                        final int offset = name.lastIndexOf('[');
                        if (offset != -1 && offset < name.length() - 1) {
                            int idx = NumberUtils.toInt(name.substring(offset + 1, name.length() - 1), -1);
                            if (idx >= 0) {
                                Map<String, String> item = items.get(idx);
                                if (item == null) {
                                    item = new HashMap<String, String>();
                                    items.put(idx, item);
                                }
                                String value = func == null
                                        ? request.getParameter(name)
                                        : func.apply(request.getParameter(name));
                                if (StringUtil.isNotEmpty(value)) {
                                    item.put(name.substring(0, offset), value);
                                }
                            }
                        }
                    }
                }
            }

            if (items.size() > 0) {
                Collection<Map<String, String>> params = new ArrayList<Map<String, String>>(items.size());
                for (Entry<Integer, Map<String, String>> entry : items.entrySet()) {
                    params.add(entry.getValue());
                }
                return params;
            }
        }
        return Collections.emptyList();
    }

    /**
     * @param request
     * @param name
     * @return
     */
    public static String getString(HttpServletRequest request, String name) {
        return getString(request, name, null);
    }

    public static String getUrl(HttpServletRequest request, String name) {
        String value = getRawString(request, name);
        if (value != null) {
            return escapeUrl(unescapeHtml(value));
        }
        return null;
    }

    /**
     * @param request
     * @param name
     * @param def
     * @return
     */
    public static String getString(HttpServletRequest request, String name, String def) {
        String value = getRawString(request, name);
        if (value != null) {
            return escapeHtml(unescapeHtml(value));
        }
        return def;
    }

    /**
     * @param request
     * @param name
     * @return
     */
    public static int getAsInt(HttpServletRequest request, String name) {
        return getAsInt(request, name, 0);
    }

    /**
     * @param request
     * @param name
     * @param def
     * @return
     */
    public static int getAsInt(HttpServletRequest request, String name, int def) {
        return NumberUtils.toInt(getRawString(request, name), def);
    }

    /**
     * @param request
     * @param name
     * @return
     */
    public static long getAsLong(HttpServletRequest request, String name) {
        return getAsLong(request, name, 0);
    }

    /**
     * @param request
     * @param name
     * @param def
     * @return
     */
    public static long getAsLong(HttpServletRequest request, String name, long def) {
        return NumberUtils.toLong(getRawString(request, name), def);
    }

    public static boolean getAsBoolean(HttpServletRequest request, String name) {
        return getAsBoolean(request, name, false);
    }

    /**
     * @param request
     * @param name
     * @param def
     * @return
     */
    public static boolean getAsBoolean(HttpServletRequest request, String name, boolean def) {
        return BooleanUtil.parse(getRawString(request, name), def);
    }

    public static String getReferer(HttpServletRequest request) {
        return request.getHeader("Referer");
    }

    private static Whitelist HTML_WHITELIST = Whitelist.none();

    static {
        HTML_WHITELIST.addTags("a",
                               "b",
                               "blockquote",
                               "br",
                               "caption",
                               "cite",
                               "code",
                               "col",
                               "colgroup",
                               //
                               "dd",
                               "div",
                               "dl",
                               "dt",
                               "em",
                               "h1",
                               "h2",
                               "h3",
                               "h4",
                               "h5",
                               "h6",
                               "i",
                               "img",
                               "li",
                               "ol",
                               //
                               "p",
                               "pre",
                               "q",
                               "small",
                               "strike",
                               "strong",
                               "sub",
                               "sup",
                               "table",
                               "tbody",
                               "td",
                               "tfoot",
                               //
                               "th",
                               "thead",
                               "tr",
                               "u",
                               "ul")//
                      .addAttributes("a", "href", "title")//
                      .addAttributes("blockquote", "cite")//
                      .addAttributes("col", "span", "width")//
                      .addAttributes("colgroup", "span", "width")//
                      .addAttributes("img", "align", "alt", "height", "src", "title", "width")//
                      .addAttributes("ol", "start", "type")//
                      .addAttributes("q", "cite")//
                      .addAttributes("table", "summary")//
                      .addAttributes("td", "abbr", "axis", "colspan", "rowspan")//
                      .addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope")//
                      .addAttributes("ul", "type")//
                      .addProtocols("a", "href", "ftp", "http", "https", "mailto")//
                      .addProtocols("blockquote", "cite", "http", "https")//
                      .addProtocols("cite", "cite", "http", "https")//
                      .addProtocols("img", "src", "http", "https")//
                      .addProtocols("q", "cite", "http", "https");
    }

    private static Whitelist LINK_ONLY = Whitelist.none();

    static {
        LINK_ONLY.addTags("a")//
                 .addAttributes("a", "href", "title")//
                 .addProtocols("a", "href", "ftp", "http", "https", "mailto");
    }

    public static String cleanHtmlAsText(String html) {
        if (StringUtil.isNotEmpty(html)) {
            return Jsoup.clean(html, Whitelist.none());
        }
        return html;
    }

    public static String cleanImages(String html) {
        if (StringUtil.isNotEmpty(html)) {
            html = html.replaceAll("(?:\r\n)", "__CRLF__");
            html = html.replaceAll("(?:\r)", "__CR__");
            html = html.replaceAll("(?:\n)", "__LF__");
            Document doc = Jsoup.parseBodyFragment(html);
            if (doc != null) {
                doc.outputSettings().prettyPrint(false);
                Elements elements = doc.getElementsByTag("img");
                if (elements != null && elements.size() > 0) {
                    elements.remove();
                }
                html = doc.getElementsByTag("body").first().html();
            }
            html = html.replaceAll("[\r\n]", StringConsts.EMPTY);
            html = html.replaceAll("__CRLF__", StringConsts.CRLF);
            html = html.replaceAll("__CR__", "\r");
            html = html.replaceAll("__LF__", "\n");
        }
        return html;
    }

    public static String cleanHtml(String html) {
        if (StringUtil.isNotEmpty(html)) {
            return Jsoup.clean(html, HTML_WHITELIST);
        }
        return html;
    }

    public static String cleanToLinkOnly(String html) {
        if (StringUtil.isNotEmpty(html)) {
            return Jsoup.clean(html, LINK_ONLY);
        }
        return html;
    }

    public static Set<String> EXT_OTHER = new HashSet<String>();
    public static Set<String> EXT_IMAGE = new HashSet<String>();
    public static Set<String> EXT_ALL = new HashSet<String>();

    static {
        for (String ext : new String[]{
                "txt", "pdf", "doc", "docx", "dot", "pdg",
                "rtf", "caj", "xls", "xlsx", "csv", "xml", "wps",
                "ppt", "pptx", "pps", "chm", "vip", "mht",
                "mp3", "mp4", "wav", "wmv", "dat", "rm", "wma", "mpg", "rmvb",
                "mpe", "mpa", "m15", "m1v", "mp2", "ram",
                "zip", "rar", "gz", "tar", "7z", "m4v", "epub",
                "swf", "flv", "fla", "swc", "swt", "dpt", "et", "apk", "ipa", "key", "avi", "eml"}) {
            EXT_OTHER.add(ext);
        }

        for (String ext : new String[]{
                "bmp", "jpg", "jpeg", "tiff", "gif", "pcx", "tga", "exif",
                "fpx", "svg", "psd", "cdr", "pcd", "dxf", "ufo",
                "eps", "ai", "raw", "png", "dcm"}) {
            EXT_IMAGE.add(ext);
        }

        EXT_ALL.addAll(EXT_OTHER);
        EXT_ALL.addAll(EXT_IMAGE);
    }

    public static String addProtocalIfPossible(String url) {
        if (StringUtil.isNotEmpty(url)) {
            String normed = url.toLowerCase().trim();
            if (!(normed.startsWith("http://") || normed.startsWith("https://") || normed.startsWith("/"))) {
                return "http://" + url;
            }
        }
        return url;
    }

    private static final String WEIXIN_BROWSER_PREFIX = "micromessenger/";

    public static boolean isWeixinEmbeddedBrowser(HttpServletRequest request) {
        String agent = request.getHeader(StringConsts.HTTP_HEADER_USER_AGENT);
        if (StringUtil.isNotEmpty(agent)) {
            return agent.toLowerCase().indexOf(WEIXIN_BROWSER_PREFIX) != -1;
        }

        return false;
    }

    public static int getVersionOfWeixinEmbeddedBrowser(HttpServletRequest request) {
        String agent = request.getHeader(StringConsts.HTTP_HEADER_USER_AGENT);
        if (StringUtil.isNotEmpty(agent)) {
            agent = agent.toLowerCase();
            int idx = agent.indexOf(WEIXIN_BROWSER_PREFIX);
            if (idx != -1) {
                idx = idx + WEIXIN_BROWSER_PREFIX.length();
                int dotindx = agent.indexOf('.', idx + 1);
                if (dotindx != -1) {
                    return NumberUtils.toInt(agent.substring(idx, dotindx));
                }
            }
        }
        return 0;
    }

    public static final class DataSource {
        public static final int BBS = 1;
        public static final int BIOMART = 2;
        public static final int JOBMD = 3;
        public static final int UC = 4;
        public static final int PAPER = 5;
        public static final int SSO = 6;
        public static final int BLOG = 7;
        public static final int EXCEL = 8;
        // public static final int SURVEY = 9;
        public static final int IDENTIFY_DOCTOR = 10;
        public static final int PHARMA_DB = 11;
        public static final int DATA_ANALYZE = 12;
        public static final int ASK = 13;
        public static final int ADS = 14;
        public static final int IDENTIFY_EXPERT = 15;
        public static final int OSTEOLOGY = 16;
        public static final int COMPENSATIONAL_SURVEY = 17;
        public static final int BIOMART_TRY = 18;
        public static final int BIOMART_GROUP = 19;
        public static final int BIOMART_DEMAND = 20;
        public static final int JOBMD_EN = 21;
        public static final int SMS = 50;
        public static final int IPHONE = 30;
        public static final int IPAD = 31;
        public static final int ANDROID = 32;
        public static final int TOPIC_FEED = 40;
        public static final int HOSPITAL = 65;

        public static final class Group {
            public static final Collection<Integer> JOBMD = new ArrayList<Integer>(2);

            static {
                JOBMD.add(DataSource.JOBMD);
                JOBMD.add(DataSource.JOBMD_EN);
            }
        }
    }

    private static Pattern getVCPattern = Pattern.compile("([0-9.]+)");
    private static Pattern getVCFromUAPattern = Pattern.compile("dxyapp_version/([0-9\\.]+)");

    public static String getAppVersion(HttpServletRequest request) {
        String versionString = getRawString(request, "vc");
        String vc = getVCFromSource(versionString, getVCPattern);
        if (StringUtil.isEmpty(vc)) {
            vc = getVCFromSource(getUserAgent(request), getVCFromUAPattern);
        }
        return StringUtil.defaultString(vc);
    }

    private static String getVCFromSource(String source, Pattern pattern) {
        if (StringUtil.isNotEmpty(source) && pattern != null) {
            Matcher matcher = pattern.matcher(source);
            if (matcher.find()) {
                try {
                    return matcher.group(1);
                } catch (Throwable e) {
                    return "";
                }
            }
        }
        return StringConsts.EMPTY;
    }

    private static Pattern isDxyAppFromWebViewByUaPattern = Pattern.compile("dxyapp");

    public static boolean isDxyAppFromWebViewByUA(HttpServletRequest request) {
        if (request != null) {
            String ua = getUserAgent(request);
            if (StringUtil.isNotEmpty(ua)) {
                Matcher matcher = isDxyAppFromWebViewByUaPattern.matcher(ua.toLowerCase());
                if (matcher.find()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getAppNameByUA(HttpServletRequest request) {
        if (request != null) {
            String ua = getUserAgent(request);
            if (StringUtil.isNotEmpty(ua)) {
                if (ua.contains("dxyapp_name/idxyer")) {
                    return "idxyer";
                } else if (ua.contains("dxyapp_name/medtime")) {
                    return "medtime";
                } else if (ua.contains("dxyapp_name/drugs")) {
                    return "drugs";
                }
            }
        }
        return StringConsts.EMPTY;
    }

    public static Object getController(Object handler) {
        if (handler != null && handler instanceof HandlerMethod) {
            return ((HandlerMethod) handler).getBean();
        }
        return handler;
    }

    public static String sendError(HttpServletResponse response, int code) {
        try {
            response.sendError(code);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sendNotFoundError(HttpServletResponse response) {
        return sendError(response, HttpServletResponse.SC_NOT_FOUND);
    }

    public static String sendInternalServerError(HttpServletResponse response) {
        return sendError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
