package com.zjicm.resume.beans;

import com.zjicm.resume.domain.Resume;
import com.zjicm.student.domain.Student;

import java.util.Date;

/**
 * 简历输出对象
 * <p>
 * Created by yujing on 2017/4/22.
 */
public class ResumeOut {
    private String name;
    private String sex;
    private String tel;
    private String email;
    private Date birth;
    private String nation;
    private String politics;

    private String school = "浙江传媒学院";
    private Resume resume;

    public ResumeOut(Resume resume, Student student) {
        if (resume == null || student == null) return;
        this.name = student.getName();
        this.sex = student.getSex();
        this.tel = student.getTel();
        this.email = student.getEmail();
        this.birth = student.getBirth();
        this.nation = student.getNation();
        this.politics = student.getPolitics();
        this.resume = resume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
