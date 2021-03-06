package com.zjicm.shortterm.beans;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


/**
 * 短学期创建参数
 * <p>
 * Created by yujing on 2017/5/12.
 */
public class ProjectParams {
    @NotBlank
    @Length(max = 30)
    private String name;
    private String company_number;
    private int institute = 11;
    @NotBlank
    @Length(max = 500)
    private String purpose;
    @NotBlank
    @Length(max = 30)
    private String place;
    @NotBlank
    @Length(max = 1000)
    private String content;
    @Length(max = 4)
    private String major_need;
    @Length(max = 8)
    private String grade_need;
    @Range(min = 10, max = 100)
    private int top_num;
    private int unmajor_num;
    private int att_id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany_number() {
        return company_number;
    }

    public void setCompany_number(String company_number) {
        this.company_number = company_number;
    }

    public int getInstitute() {
        return institute;
    }

    public void setInstitute(int institute) {
        this.institute = institute;
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

    public String getGrade_need() {
        return grade_need;
    }

    public void setGrade_need(String grade_need) {
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
