package com.zjicm.auth.beans;

import com.zjicm.company.domain.Company;
import com.zjicm.student.domain.Student;
import com.zjicm.teacher.domain.Teacher;

import java.util.List;

/**
 * 用户信息输出类
 * <p>
 * Created by yujing on 2017/5/10.
 */
public class AccountUserOut {
    private int userId;
    private String number;
    private int roleId;

    private Teacher teacher;
    private Student student;
    private Company company;
    private List<RoutesOut> routes;

    public AccountUserOut(int userId, String number, int roleId) {
        this.userId = userId;
        this.number = number;
        this.roleId = roleId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<RoutesOut> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RoutesOut> routes) {
        this.routes = routes;
    }
}
