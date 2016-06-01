package com.zjicm.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * PracticeInfo entity. @author MyEclipse Persistence Tools
 */

public class PracticeInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Student student;
	private Company company;
	private String company_1;
	private String position;
	private String purpose;
	private String provice;
	private String city;
	private String location;
	private short status;
	private Set practiceReports = new HashSet(0);

	// Constructors

	/** default constructor */
	public PracticeInfo() {
	}

	/** minimal constructor */
	public PracticeInfo(Student student, String company_1, String position,
			String purpose, String location, short status) {
		this.student = student;
		this.company_1 = company_1;
		this.position = position;
		this.purpose = purpose;
		this.location = location;
		this.status = status;
	}

	/** full constructor */
	public PracticeInfo(Student student, Company company, String company_1,
			String position, String purpose, String location, short status,
			Set practiceReports) {
		this.student = student;
		this.company = company;
		this.company_1 = company_1;
		this.position = position;
		this.purpose = purpose;
		this.location = location;
		this.status = status;
		this.practiceReports = practiceReports;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getCompany_1() {
		return this.company_1;
	}

	public void setCompany_1(String company_1) {
		this.company_1 = company_1;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	
	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public short getStatus() {
		return this.status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Set getPracticeReports() {
		return this.practiceReports;
	}

	public void setPracticeReports(Set practiceReports) {
		this.practiceReports = practiceReports;
	}

}