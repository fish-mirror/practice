package com.zjicm.entity;

/**
 * ShortTermComment entity. @author MyEclipse Persistence Tools
 */

public class ShortTermComment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer rid;
	private User user;
	private String stuId;
	private String comment;
	private float grade;

	// Constructors

	/** default constructor */
	public ShortTermComment() {
	}
	public ShortTermComment(Integer id) {
		this.id = id;
	}

	

	/** minimal constructor */
	public ShortTermComment(Integer rid,User user, String stuId, float grade) {
		this.rid = rid;
		this.user = user;
		this.stuId = stuId;
		this.grade = grade;
	}

	/** full constructor */
	public ShortTermComment(Integer rid,User user, String stuId, float grade, String comment) {
		this.rid = rid;
		this.user = user;
		this.stuId = stuId;
		this.comment = comment;
		this.grade = grade;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public float getGrade() {
		return this.grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	
}