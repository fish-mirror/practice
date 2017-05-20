package com.zjicm.practice.beans;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 实习信息创建参数
 * <p>
 * Created by yujing on 2017/5/20.
 */
public class PracticeParams {

    private String company_number;
    @NotNull
    @Length(max = 20)
    private String company_name;
    @NotNull
    @Length(max = 20)
    private String job;
    @NotNull
    @Length(max = 10)
    private String linkman;
    @NotNull
    @Length(max = 16)
    private String cellphone;
    @NotNull
    @Length(max = 20)
    private String address;
    @NotNull
    @Length(max = 10)
    private String province;
    @NotNull
    @Length(max = 10)
    private String city;
    @NotNull
    @Length(max = 0)
    private String purpose;

    public String getCompany_number() {
        return company_number;
    }

    public void setCompany_number(String company_number) {
        this.company_number = company_number;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
