package com.zjicm.student.beans;

/**
 * Created by yujing on 2017/4/13.
 */
public class ClassInfoOut {
    private int grade;
    private String major;
    private int classIndex;
    private String classname;

    public ClassInfoOut(ClassInfoDto dto) {
        this.grade = dto.getGrade();
        this.major = dto.getMajor();
        this.classIndex = dto.getClassIndex();
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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
