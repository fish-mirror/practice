package com.zjicm.practice.beans;

import javax.validation.constraints.NotNull;

/**
 * 实习周记参数
 * <p>
 * Created by yujing on 2017/5/21.
 */
public class WeeklyDataParams {
    @NotNull
    private String content;
    @NotNull
    private String province;
    @NotNull
    private String city;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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
