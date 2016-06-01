package com.zjicm.entity;

import java.util.Date;

/**
 * Intention entity. @author MyEclipse Persistence Tools
 */

public class Intention implements java.io.Serializable {

	// Fields

	private Integer id;
	private String colId;
	private String institute;
	private String tittle;
	private String content;
	private Date date;

	// Constructors

	/** default constructor */
	public Intention() {
	}

	public Intention(Integer id) {
		this.id = id;
	}
	/** full constructor */
	public Intention(String colId,String institute, String tittle, String content,
			Date date) {
		this.colId = colId;
		this.institute = institute;
		this.tittle = tittle;
		this.content = content;
		this.date = date;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getColId() {
		return colId;
	}

	public void setColId(String colId) {
		this.colId = colId;
	}

	public String getTittle() {
		return this.tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}