package com.zjicm.entity;

/**
 * ShortTermProject entity. @author MyEclipse Persistence Tools
 */

public class ShortTermProject implements java.io.Serializable {

	// Fields

	private Integer id;
	private Company company;
	private String name;
	private String institute;
	private String purpose;
	private String content;
	private String majorNeed;
	private String gradeNeed;
	private Integer selectedNum;
	private Integer topNum;
	private Integer unmajorNum;
	private Integer unmajorSelected;
	private String term;
	private String place;
	private String time;
	private short status;
	private String fileUrl;
	
	// Constructors

	/** default constructor */
	public ShortTermProject() {
	}
	public ShortTermProject(Integer id) {
		this.id = id;
	}
	/** minimal constructor */
	public ShortTermProject(String name, String institute, short status) {
		this.name = name;
		this.institute = institute;
		this.status = status;
	}
	
	public ShortTermProject(String term, String name, Company company, String place) {
		this.term = term;
		this.company = company;
		this.name = name;
		this.place = place;
	}

	/** full constructor */
	public ShortTermProject(Integer id,Company company, String name, String institute,
			String purpose, String content, String majorNeed,String gradeNeed,
			Integer topNum, Integer unmajorNum, String term, String place,
			String time, short status) {
		this.id = id;
		this.company = company;
		this.name = name;
		this.institute = institute;
		this.purpose = purpose;
		this.content = content;
		this.majorNeed = majorNeed;
		this.gradeNeed = gradeNeed;
		this.topNum = topNum;
		this.unmajorNum = unmajorNum;
		this.term = term;
		this.place = place;
		this.time = time;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMajorNeed() {
		return this.majorNeed;
	}

	public void setMajorNeed(String majorNeed) {
		this.majorNeed = majorNeed;
	}

	public String getGradeNeed() {
		return gradeNeed;
	}

	public void setGradeNeed(String gradeNeed) {
		this.gradeNeed = gradeNeed;
	}

	public Integer getSelectedNum() {
		return this.selectedNum;
	}

	public void setSelectedNum(Integer selectedNum) {
		this.selectedNum = selectedNum;
	}

	public Integer getTopNum() {
		return this.topNum;
	}

	public void setTopNum(Integer topNum) {
		this.topNum = topNum;
	}

	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public short getStatus() {
		return this.status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getUnmajorNum() {
		return unmajorNum;
	}

	public void setUnmajorNum(Integer unmajorNum) {
		this.unmajorNum = unmajorNum;
	}

	public Integer getUnmajorSelected() {
		return unmajorSelected;
	}

	public void setUnmajorSelected(Integer unmajorSelected) {
		this.unmajorSelected = unmajorSelected;
	}
	
}