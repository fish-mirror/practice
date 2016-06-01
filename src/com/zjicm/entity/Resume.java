package com.zjicm.entity;
// default package

import com.zjicm.entity.Student;


/**
 * Resume entity. @author MyEclipse Persistence Tools
 */

public class Resume  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String stuId;
     private String tittle;
     private String majorClass;
     private String schoolExp;
     private String practiceExp;
     private String certificate;
     private String selfComment;
     private String worksUrl;
     private String resumeUrl;


    // Constructors

    /** default constructor */
    public Resume() {
    }

    public Resume(Integer id){
    	this.id = id;
    }
    public Resume(String stuId, String tittle, String majorClass, String schoolExp, String practiceExp, String certificate, String selfComment) {
        this.stuId = stuId;
        this.tittle = tittle;
        this.majorClass = majorClass;
        this.schoolExp = schoolExp;
        this.practiceExp = practiceExp;
        this.certificate = certificate;
        this.selfComment = selfComment;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    

    public String getStuId() {
		return stuId;
	}
    

	public String getTittle() {
		return tittle;
	}


	public void setTittle(String tittle) {
		this.tittle = tittle;
	}


	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	
	public String getMajorClass() {
        return this.majorClass;
    }
    
    public void setMajorClass(String majorClass) {
        this.majorClass = majorClass;
    }

    public String getSchoolExp() {
        return this.schoolExp;
    }
    
    public void setSchoolExp(String schoolExp) {
        this.schoolExp = schoolExp;
    }

    public String getPracticeExp() {
        return this.practiceExp;
    }
    
    public void setPracticeExp(String practiceExp) {
        this.practiceExp = practiceExp;
    }

    public String getCertificate() {
        return this.certificate;
    }
    
    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getSelfComment() {
        return this.selfComment;
    }
    
    public void setSelfComment(String selfComment) {
        this.selfComment = selfComment;
    }

    public String getWorksUrl() {
        return this.worksUrl;
    }
    
    public void setWorksUrl(String worksUrl) {
        this.worksUrl = worksUrl;
    }

    public String getResumeUrl() {
        return this.resumeUrl;
    }
    
    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }


}