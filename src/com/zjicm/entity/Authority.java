package com.zjicm.entity;
// default package



/**
 * Authority entity. @author MyEclipse Persistence Tools
 */

public class Authority  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String collegeId;
     private String authority;


    // Constructors

    /** default constructor */
    public Authority() {
    }

    
    /** full constructor */
    public Authority(String collegeId, String authority) {
        this.collegeId = collegeId;
        this.authority = authority;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }


    public String getCollegeId() {
		return collegeId;
	}


	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}


	public String getAuthority() {
        return this.authority;
    }
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }
   








}