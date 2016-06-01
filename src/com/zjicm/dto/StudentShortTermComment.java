package com.zjicm.dto;
// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;



public class StudentShortTermComment  implements java.io.Serializable {


    // Fields    

     private String stuId;
     private String name;
     private String institute;
     private String classname;
     
     private String stpId;
     private String stpName;
     
     private float grade;
     
     public StudentShortTermComment(){
    	 
     }

	public StudentShortTermComment(String stuId, String name, String institute,
			String classname, String stpId, String stpName, float grade) {
		super();
		this.stuId = stuId;
		this.name = name;
		this.institute = institute;
		this.classname = classname;
		this.stpId = stpId;
		this.stpName = stpName;
		this.grade = grade;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getStpId() {
		return stpId;
	}

	public void setStpId(String stpId) {
		this.stpId = stpId;
	}

	public String getStpName() {
		return stpName;
	}

	public void setStpName(String stpName) {
		this.stpName = stpName;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}
	
}