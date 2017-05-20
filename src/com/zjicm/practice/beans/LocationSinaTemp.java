package com.zjicm.practice.beans;

/**
 * 新浪获取位置信息的响应消息模板类
 * <p>
 * Created by yujing on 2017/5/21.
 */
public class LocationSinaTemp {
    private String province;
    private String city;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}