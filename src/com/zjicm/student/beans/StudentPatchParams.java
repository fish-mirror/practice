package com.zjicm.student.beans;

import com.zjicm.common.lang.consts.RegxConsts;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 学生个人信息修改参数
 *
 * Created by yujing on 2017/4/17.
 */
public class StudentPatchParams {
    @Pattern(regexp = RegxConsts.EXPRESSION_CELLPHONE, message = "手机号格式不正确")
    private String tel;
    @Pattern(regexp = RegxConsts.EXPRESSION_EMAIL, message = "电子邮箱格式不正确")
    private String email;

    private String nation;
    private String politic;
    private String address;

    private int att_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "出生日期非法")
    private Date birthday;
    @Range(min = 0, max = 230, message = "输入有误，范围 0-230 CM")
    private int height;
    @Range(min = 0, max = 200, message = "输入有误，范围 0-200 KG")
    private int weight;

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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPolitic() {
        return politic;
    }

    public void setPolitic(String politic) {
        this.politic = politic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAtt_id() {
        return att_id;
    }

    public void setAtt_id(int att_id) {
        this.att_id = att_id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
