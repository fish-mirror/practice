package com.zjicm.auth.beans;

/**
 * 菜单项和路由对应关系
 * <p>
 * Created by yujing on 2017/5/10.
 */
public class RouteBean {
    private String name;
    private String uri;

    public RouteBean(String name) {
        this.name = name;
    }

    public RouteBean(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
