package com.zjicm.cooperation.beans;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 创建企业账号参数
 * <p>
 * Created by yujing on 2017/4/23.
 */
public class CompanyRegisterParams {
    @NotBlank
    @Length(min = 6, max = 9, message = "输入长度在 6 - 9 位")
    private String number;
    @NotBlank
    @Length(min = 8, max = 20, message = "输入长度在 8 - 20 位")
    private String password;
    @NotBlank
    @Length(min = 8, max = 20, message = "输入长度在 0 - 20 位")
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
