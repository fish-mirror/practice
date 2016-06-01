package com.zjicm.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PracticeReport entity. @author MyEclipse Persistence Tools
 */

public class PracticeReport implements java.io.Serializable {

	// Fields

	private Integer id;
	private PracticeInfo practiceInfo;
	private Student student;
	private String url;
	private Date date;
	private Set practiceComments = new HashSet(0);

	// Constructors

	/** default constructor */
	public PracticeReport() {
	}

	/** minimal constructor */
	public PracticeReport(PracticeInfo practiceInfo, Student student,
			String url, Date date) {
		this.practiceInfo = practiceInfo;
		this.student = student;
		this.url = url;
		this.date = date;
	}

	/** full constructor */
	public PracticeReport(PracticeInfo practiceInfo, Student student,
			String url, Date date, Set practiceComments) {
		this.practiceInfo = practiceInfo;
		this.student = student;
		this.url = url;
		this.date = date;
		this.practiceComments = practiceComments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PracticeInfo getPracticeInfo() {
		return this.practiceInfo;
	}

	public void setPracticeInfo(PracticeInfo practiceInfo) {
		this.practiceInfo = practiceInfo;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set getPracticeComments() {
		return this.practiceComments;
	}

	public void setPracticeComments(Set practiceComments) {
		this.practiceComments = practiceComments;
	}

}