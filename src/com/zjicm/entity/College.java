package com.zjicm.entity;

// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * College entity. @author MyEclipse Persistence Tools
 */

public class College  implements java.io.Serializable {


    // Fields    

     private String id;
     private String name;
     private String sex;
     private String institute;
     private String position;
     private String tel;
     private Date birth;
     private Date enterDate;
     private String imgUrl;
     private Set authorities = new HashSet(0);


    // Constructors

    /** default constructor */
    public College() {
    }

	/** minimal constructor */
    public College(String id, String name, String sex, String institute, String position, String tel) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.institute = institute;
        this.position = position;
        this.tel = tel;
    }
    
    /** full constructor */
    public College(String id, String name, String sex, String institute, String position, String tel, Date birth, Date enterDate, String imgUrl, Set authorities) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.institute = institute;
        this.position = position;
        this.tel = tel;
        this.birth = birth;
        this.enterDate = enterDate;
        this.imgUrl = imgUrl;
        this.authorities = authorities;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
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

    public Set getAuthorities() {
        return this.authorities;
    }
    
    public void setAuthorities(Set authorities) {
        this.authorities = authorities;
    }
   








}