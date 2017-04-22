package com.zjicm.student.beans;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 教师可修改的学生学院信息
 * <p>
 * Created by yujing on 2017/4/17.
 */
public class StudentInstituteParams {
    private String name;
    @Range(min = 0, max = 2, message = "请输入正确性别")
    private int sex;
    @Range(min = 0, max = 13, message = "请输入合法学院代号")
    private int institute;
    @Range(min = 0, max = 30, message = "请输入正确的年级")
    private int grade;
    @Length(min = 4, max = 4, message = "请输入合法专业代号")
    private String major;
    @Range(min = 0, max = 3, message = "请输入合法班级号")
    private int class_index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getInstitute() {
        return institute;
    }

    public void setInstitute(int institute) {
        this.institute = institute;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getClass_index() {
        return class_index;
    }

    public void setClass_index(int class_index) {
        this.class_index = class_index;
    }
}
