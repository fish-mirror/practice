package com.zjicm.entity;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User  implements java.io.Serializable {


    // Fields    

     private String id;
     private String pwd;
     private short roleId;


    // Constructors

    /** default constructor */
    public User() {
    }
    public User(String id) {
    	this.id = id;
    }

	/** minimal constructor */
    public User(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }
    
    /** full constructor */
    public User(String id, String pwd, short roleId) {
        this.id = id;
        this.pwd = pwd;
        this.roleId = roleId;
        
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return this.pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public short getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(short roleId) {
        this.roleId = roleId;
    }
   
}