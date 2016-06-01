package com.zjicm.dto;
// default package

import java.util.Date;

public class StudentShortTermReport  implements java.io.Serializable {


    // Fields    

     private String stuId;
     private String name;
     private String institute;
     private String classname;
     
     private Integer stpId;
     private String stpName;
     
     private Integer strId;
     private String url;
     private Date date;
     
     public StudentShortTermReport(){
    	 
     }
     
	 

	public StudentShortTermReport(String stuId, String name, String institute,
			String classname, Integer stpId, String stpName, Integer strId, String url, Date date) {
		super();
		this.stuId = stuId;
		this.name = name;
		this.institute = institute;
		this.classname = classname;
		this.stpId = stpId;
		this.stpName = stpName;
		this.strId = strId;
		this.url = url;
		this.date = date;
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
		
	public Integer getStpId() {
		return stpId;
	}

	public void setStpId(Integer stpId) {
		this.stpId = stpId;
	}

	public String getStpName() {
		return stpName;
	}
		
	public void setStpName(String stpName) {
		this.stpName = stpName;
	}

	public Integer getStrId() {
		return strId;
	}
		
	public void setStrId(Integer strId) {
		this.strId = strId;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDate() {
		return date;
	}
		
	public void setDate(Date date) {
		this.date = date;
	}
    
}