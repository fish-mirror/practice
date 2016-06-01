package com.zjicm.dto;
// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;


/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class StudentDTO  implements java.io.Serializable {


    // Fields    

     private String id;
     private String name;
    
     private String institute;
    
     private String classname;
  
    // Constructors

    /** default constructor */
    public StudentDTO() {
    }
    public StudentDTO(String id) {
    	this.id = id;
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

    public String getInstitute() {
        return this.institute;
    }
    
    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getClassname() {
        return this.classname;
    }
    
    public void setClassname(String classname) {
        this.classname = classname;
    }

}