package com.zjicm.common.beans;

import com.zjicm.auth.domain.User;
import com.zjicm.company.domain.Company;
import com.zjicm.student.domain.Student;
import com.zjicm.teacher.domain.Teacher;

import java.util.List;
import java.util.Set;

/**
 * Created by yujing on 2017/3/10.
 */
public class UserSession {
    private int userId;
    private String number;
    private String name;
    private int roleId;
    private Set<Integer> authority;
    private String institute;
    private String major;
    private String classname;

    public UserSession() {

    }

    public UserSession(int userId,
                       String number,
                       String name,
                       int roleId,
                       Set<Integer> authority,
                       String institute,
                       String major, String classname) {
        this.userId = userId;
        this.number = number;
        this.name = name;
        this.roleId = roleId;
        this.authority = authority;
        this.institute = institute;
        this.major = major;
        this.classname = classname;
    }

    public void set(User user) {
        if (user == null) return;
        this.setUserId(user.getId());
        this.setNumber(user.getNumber());
        this.setRoleId(user.getRoleId());
        this.setAuthority(user.getAuthorities());
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
        this.setMajor(student.getMajor());
        this.setClassname(student.getClassname());
    }

    public void set(User user, Company company) {
        this.set(user);
        if (company == null) return;
        this.setName(company.getName());
    }

    public boolean isLogin() {
        return userId > 0;
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

    public Set<Integer> getAuthority() {
        return authority;
    }

    public void setAuthority(Set<Integer> authority) {
        this.authority = authority;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
