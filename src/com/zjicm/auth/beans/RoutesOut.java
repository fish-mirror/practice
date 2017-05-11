package com.zjicm.auth.beans;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 输出菜单路由组
 *
 * Created by yujing on 2017/5/10.
 */
public class RoutesOut {
    private String name;
    private List<RouteBean> routes;

    public RoutesOut(String name, RouteBean... childs) {
        this.name = name;

        if (ArrayUtils.isEmpty(childs)) return;

        routes = new ArrayList<>();
        for (RouteBean child : childs) {
            routes.add(child);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RouteBean> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteBean> routes) {
        this.routes = routes;
    }

    public List<RouteBean> addRoute(RouteBean routeBean) {
        if (CollectionUtils.isEmpty(this.routes)) {
            this.routes = new ArrayList<>();
        }
        routes.add(routeBean);
        return this.routes;
    }
}
