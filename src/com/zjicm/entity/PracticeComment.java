package com.zjicm.entity;

/**
 * PracticeComment entity. @author MyEclipse Persistence Tools
 */

public class PracticeComment implements java.io.Serializable {

	// Fields

	private Integer id;
	private College college;
	private PracticeReport practiceReport;
	private String comment;
	private float grade;

	// Constructors

	/** default constructor */
	public PracticeComment() {
	}

	/** minimal constructor */
	public PracticeComment(College college,
			PracticeReport practiceReport, float grade) {
		this.college = college;
		this.practiceReport = practiceReport;
		this.grade = grade;
	}

	/** full constructor */
	public PracticeComment(College college,
			PracticeReport practiceReport, String comment, float grade) {
		this.college = college;
		this.practiceReport = practiceReport;
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

	public College getCollege() {
		return this.college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public PracticeReport getPracticeReport() {
		return this.practiceReport;
	}

	public void setPracticeReport(PracticeReport practiceReport) {
		this.practiceReport = practiceReport;
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