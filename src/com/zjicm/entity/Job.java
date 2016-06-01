package com.zjicm.entity;

/**
 * Job entity. @author MyEclipse Persistence Tools
 */

public class Job implements java.io.Serializable {

	// Fields

	private Integer id;
	private Company company;
	private String name;
	private String desc;
	private String need;
	private String time;
	private String province;
	private String city;
	private String place;
	private Integer needNum;
	private Integer haveNum;
    private short status;

	// Constructors

	/** default constructor */
	public Job() {
	}

	/** minimal constructor */
	public Job(Company company) {
		this.company = company;
	}

	/** full constructor */
	public Job(Company company, String name, String desc, String need, String time,
			String province, String city, String place, Integer needNum,
			Integer haveNum,short status) {
		this.company = company;
		this.name = name;
		this.desc = desc;
		this.need = need;
		this.time = time;
		this.province = province;
		this.city = city;
		this.place = place;
		this.needNum = needNum;
		this.haveNum = haveNum;
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
		return company;
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

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getNeed() {
		return this.need;
	}

	public void setNeed(String need) {
		this.need = need;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getNeedNum() {
		return this.needNum;
	}

	public void setNeedNum(Integer needNum) {
		this.needNum = needNum;
	}

	public Integer getHaveNum() {
		return this.haveNum;
	}

	public void setHaveNum(Integer haveNum) {
		this.haveNum = haveNum;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	
}