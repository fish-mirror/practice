package com.zjicm.entity;
// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;


/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student  implements java.io.Serializable {


    // Fields    

     private String id;
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
    // Constructors

    /** default constructor */
    public Student() {
    }
    public Student(String id) {
    	this.id = id;
    }

	/** minimal constructor */
    public Student(String id, String name, String sex, String institute, String major, String classname, String tel, short graduate) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.institute = institute;
        this.major = major;
        this.classname = classname;
        this.tel = tel;
        this.graduate =graduate;
    }
    
    /** full constructor */
    public Student(String id, String name, String sex, String institute, String major, String classname, String tel, Date birth, String politics, short status, String imgUrl, short graduate) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.institute = institute;
        this.major = major;
        this.classname = classname;
        this.tel = tel;
        this.birth = birth;
        this.politics = politics;
        this.status = status;
        this.imgUrl = imgUrl;
        this.graduate =graduate;
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

	@Override
	public String toString() {
		return id+"\t"+name;
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
	
    
}