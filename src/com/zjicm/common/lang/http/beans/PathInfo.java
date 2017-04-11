package com.zjicm.common.lang.http.beans;


import org.apache.commons.lang.math.NumberUtils;

public class PathInfo {
    public static final PathInfo DEFAULT = new PathInfo();
    private String[] segments = null;

    public PathInfo() {
    }

    public int length() {
        return this.segments == null ? 0 : this.segments.length;
    }

    public PathInfo(String pathInfo) {
        if (pathInfo != null) {
            this.segments = pathInfo.split("/");
        }
    }

    public String getString(int index) {
        if (segments == null || segments.length <= index) {
            return null;
        }

        return segments[index];
    }

    public int getInt(int index) {
        return NumberUtils.toInt(getString(index), 0);
    }
}
