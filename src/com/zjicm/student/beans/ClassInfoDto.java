package com.zjicm.student.beans;

/**
 * Created by yujing on 2017/4/13.
 */
public class ClassInfoDto {
    private int grade;
    private String major;
    private int classIndex;

    public ClassInfoDto() {
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

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }
}
