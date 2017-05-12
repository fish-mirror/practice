package com.zjicm.shortterm.beans;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


/**
 * 短学期创建参数
 * <p>
 * Created by yujing on 2017/5/12.
 */
public class ProjectParams {
    @NotBlank
    private String name;
    @Range(min = 1)
    private int company_user_id;
    @NotBlank
    private String purpose;
    @NotBlank
    private String place;
    @NotBlank
    private String content;
    private String major_need;
    private int grade_need;
    @Range(min = 10)
    private int top_num;
    private int unmajor_num;
    private int att_id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompany_user_id() {
        return company_user_id;
    }

    public void setCompany_user_id(int company_user_id) {
        this.company_user_id = company_user_id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMajor_need() {
        return major_need;
    }

    public void setMajor_need(String major_need) {
        this.major_need = major_need;
    }

    public int getGrade_need() {
        return grade_need;
    }

    public void setGrade_need(int grade_need) {
        this.grade_need = grade_need;
    }

    public int getTop_num() {
        return top_num;
    }

    public void setTop_num(int top_num) {
        this.top_num = top_num;
    }

    public int getUnmajor_num() {
        return unmajor_num;
    }

    public void setUnmajor_num(int unmajor_num) {
        this.unmajor_num = unmajor_num;
    }


    public int getAtt_id() {
        return att_id;
    }

    public void setAtt_id(int att_id) {
        this.att_id = att_id;
    }
}
