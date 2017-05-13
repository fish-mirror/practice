package com.zjicm.common.beans;

import com.zjicm.auth.domain.User;
import com.zjicm.company.domain.Company;
import com.zjicm.student.domain.Student;
import com.zjicm.teacher.domain.Teacher;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by yujing on 2017/3/10.
 */
public class UserSession implements Serializable {
    private String id;
    private int userId;
    private String number;
    private String name;
    private int roleId;
    private Set<Integer> authorities;
    private int institute;
    private int grade;
    private String major;
    private int classIndex;

    public UserSession(String sessionId) {
        this.id = sessionId;
    }


    public void set(User user) {
        if (user == null) return;
        this.setUserId(user.getId());
        this.setNumber(user.getNumber());
        this.setRoleId(user.getRoleId());
    }

    public void set(User user, Teacher teacher) {
        this.set(user);
        if (teacher == null) return;
        this.setName(teacher.getName());
        this.setInstitute(teacher.getInstitute());

    }

    public void set(User user, Student student) {
        this.set(user);
        if (student == null) return;
        this.setName(student.getName());
        this.setInstitute(student.getInstitute());
        this.setGrade(student.getGrade());
        this.setMajor(student.getMajor());
        this.setClassIndex(student.getClassIndex());
    }

    public void set(User user, Company company) {
        this.set(user);
        if (company == null) return;
        this.setName(company.getName());
    }

    public boolean isLogin() {
        return userId > 0;
    }

    public void logout() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Set<Integer> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Integer> authorities) {
        this.authorities = authorities;
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

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }
}
