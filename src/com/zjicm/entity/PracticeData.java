package com.zjicm.entity;

import java.util.Date;

import com.zjicm.dto.StudentDTO;

/**
 * PracticeData entity. @author MyEclipse Persistence Tools
 */

public class PracticeData implements java.io.Serializable {

	// Fields

	private Integer id;
	private StudentDTO student;
	private String content;
	private String province;
	private String city;
	private Date date;

	// Constructors

	/** default constructor */
	public PracticeData() {
	}

	/** full constructor */
	public PracticeData(StudentDTO student, String content, String province,String city,
			Date date) {
		this.student = student;
		this.content = content;
		this.province = province;
		this.city = city;
		this.date = date;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StudentDTO getStudent() {
		return this.student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}