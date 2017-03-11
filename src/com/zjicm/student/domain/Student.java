package com.zjicm.student.domain;


import com.zjicm.common.lang.sql.domain.CanonicalDomain;

import java.util.Date;

import javax.persistence.*;


/**
 * 学生数据域对象
 */
@Entity
@Table(name = "student")
public class Student implements CanonicalDomain<Integer> {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String number;
    private String name;
    private String sex;
    private String institute;
    private String major;
    private String classname;
    private String tel;
    private String email;
    private Date birth;
    private String nation;
    private String politics;
    private short status;
    private String imgUrl;
    private short graduate;
    private Double height;
    private Double weight;
    private String address;

    @Column(updatable = false)
    private Date createTime;                            // 创建时间
    private Date modifyTime;                            // 修改时间

    public Student() {
    }

    @Override
    public void prepare() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getTel() {
        return this.tel;
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
        return this.birth;
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
        return this.politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public short getGraduate() {
        return graduate;
    }

    public void setGraduate(short graduate) {
        this.graduate = graduate;
    }


    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return number + "\t" + name;
    }

}