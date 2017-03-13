package com.zjicm.common.lang.cookie;

import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHandler extends CookieGenerator {
    private boolean httpOnly = false;

    public String getCookieValue(HttpServletRequest request) {
        return CookieUtil.getValue(request, getCookieName());
    }

    public Cookie getCookie(HttpServletRequest request) {
        return CookieUtil.get(request, getCookieName());
    }

    public boolean isHttpOnly() {
        return httpOnly;
    }

    public void setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    @Override
    public void addCookie(HttpServletResponse response, String value) {
        CookieUtil.add(response,
                       getCookieName(),
                       value,
                       getCookieDomain(),
                       getCookiePath(),
                       httpOnly,
                       this.getCookieMaxAge() != null ? this.getCookieMaxAge() : -1);
    }
}
