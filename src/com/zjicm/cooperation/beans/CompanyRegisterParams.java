package com.zjicm.cooperation.beans;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 创建企业账号参数
 * <p>
 * Created by yujing on 2017/4/23.
 */
public class CompanyRegisterParams {
    @NotBlank
    private String number;
    @NotBlank
    private String password;
    @NotBlank
    private String name;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
