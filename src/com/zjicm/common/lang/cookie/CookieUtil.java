package com.zjicm.common.lang.cookie;

import com.zjicm.common.lang.character.consts.CharacterConsts;
import com.zjicm.common.lang.consts.StringConsts;
import com.zjicm.common.lang.http.util.WebUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public final class CookieUtil {
    public static void add(HttpServletResponse response, String name, String value) {
        add(response, name, value, null, null, false, -1);
    }

    public static void add(HttpServletResponse response, String name, String value, int age) {
        add(response, name, value, null, null, false, age);
    }

    public static void add(HttpServletResponse response, String name, String value, String domain, int age) {
        add(response, name, value, domain, null, false, age);
    }

    public static void add(HttpServletResponse response,
                           String name,
                           String value,
                           String domain,
                           String path,
                           boolean httpOnly,
                           int age) {
        add(response, name, value, domain, path, httpOnly, age, false);
    }

    /**
     * 通过http header添加cookie
     *
     * @param response
     * @param name
     * @param value
     * @param domain
     * @param path
     * @param httpOnly
     * @param age
     * @param secure
     */
    public static void add(HttpServletResponse response,
                           String name,
                           String value,
                           String domain,
                           String path,
                           boolean httpOnly,
                           int age,
                           boolean secure) {
        if (StringUtils.isNotEmpty(name)) {
            StringBuilder buff = new StringBuilder(128);
            buff.append(name);
            buff.append("=");
            buff.append(StringUtils.defaultString(value));
            buff.append(CharacterConsts.SEMICOLON);

            buff.append("Path=");
            buff.append(StringUtils.defaultIfEmpty(path, StringConsts.SLASH));

            if (domain != null) {
                buff.append(CharacterConsts.SEMICOLON);
                buff.append("Domain=");
                buff.append(domain);
            }

            if (age >= 0) {
                buff.append(CharacterConsts.SEMICOLON);
                buff.append("Expires=");
                buff.append(WebUtil.toString(new Date(System.currentTimeMillis() + age * 1000l)));
            }

            if (secure) {
                buff.append(CharacterConsts.SEMICOLON);
                buff.append("Secure");
            }

            if (httpOnly) {
                buff.append(CharacterConsts.SEMICOLON);
                buff.append("HTTPOnly");
            }

            response.addHeader("Set-Cookie", buff.toString());
        }
    }

    public static String getValue(HttpServletRequest request, String name) {
        Cookie cookie = get(request, name);
        if (cookie != null) {
            return cookie.getValue();
        }
        return StringConsts.EMPTY;
    }

    public static Cookie get(HttpServletRequest request, String name) {
        if (name != null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie != null && name.equals(cookie.getName())) {
                        return cookie;
                    }
                }
            }
        }

        return null;
    }

    public static void delete(HttpServletRequest request, HttpServletResponse response, String name) {
        delete(request, response, name, null, StringConsts.SLASH);
    }

    public static void delete(HttpServletRequest request,
                              HttpServletResponse response,
                              String name,
                              String domain,
                              String path) {
        if (name != null) {
            Cookie cookie = new Cookie(name, StringConsts.EMPTY);
            cookie.setMaxAge(0);
            if (domain != null) {
                cookie.setDomain(domain);
            }
            cookie.setPath(StringUtils.defaultIfEmpty(path, StringConsts.SLASH));
            response.addCookie(cookie);
        }
    }
}
