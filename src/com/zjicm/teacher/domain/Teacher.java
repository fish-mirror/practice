package com.zjicm.teacher.domain;


import com.zjicm.common.lang.sql.domain.CanonicalDomain;

import javax.persistence.*;
import java.util.Date;

/**
 * 教职工数据域对象
 */
@Entity
@Table(name = "teacher")
public class Teacher implements CanonicalDomain<Integer> {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String account;
    private String name;
    private String sex;
    private String institute;
    private String position;
    private String tel;
    private Date birth;
    private Date enterDate;
    private String imgUrl;

    @Column(updatable = false)
    private Date createTime;                            // 创建时间
    private Date modifyTime;                            // 修改时间



    public Teacher() {
    }



    @Override
    public void prepare() {

    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getInstitute() {
        return this.institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getBirth() {
        return this.birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getEnterDate() {
        return this.enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getModifyTime() {
        return modifyTime;
    }

    @Override
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}