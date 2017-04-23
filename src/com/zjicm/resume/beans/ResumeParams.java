package com.zjicm.resume.beans;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by yujing on 2017/4/23.
 */
public class ResumeParams {
    @Length(max = 30, message = "请输入 30 字以内")
    @NotBlank
    private String title;
    private String major_class;
    private String school_exp;
    private String practice_exp;
    private String certificate;
    private String self_comment;
    private String works_url;
    private String resume_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMajor_class() {
        return major_class;
    }

    public void setMajor_class(String major_class) {
        this.major_class = major_class;
    }

    public String getSchool_exp() {
        return school_exp;
    }

    public void setSchool_exp(String school_exp) {
        this.school_exp = school_exp;
    }

    public String getPractice_exp() {
        return practice_exp;
    }

    public void setPractice_exp(String practice_exp) {
        this.practice_exp = practice_exp;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getSelf_comment() {
        return self_comment;
    }

    public void setSelf_comment(String self_comment) {
        this.self_comment = self_comment;
    }

    public String getWorks_url() {
        return works_url;
    }

    public void setWorks_url(String works_url) {
        this.works_url = works_url;
    }

    public String getResume_url() {
        return resume_url;
    }

    public void setResume_url(String resume_url) {
        this.resume_url = resume_url;
    }
}
