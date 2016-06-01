package com.zjicm.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * ShortTermReport entity. @author MyEclipse Persistence Tools
 */

public class ShortTermReport implements java.io.Serializable {

	// Fields

	private Integer id;
	private String stuId;
	private String url;
	private Date date;
	private ShortTermProject shortTermProject;
	private Set shortTermComments = new HashSet(0);

	// Constructors

	/** default constructor */
	public ShortTermReport() {
	}
	public ShortTermReport(Integer id) {
		this.id = id;
	}
	
	public ShortTermReport(String stuId, ShortTermProject shortTermProject) {
		this.stuId = stuId;
		this.shortTermProject = shortTermProject;
	}

	/** minimal constructor */
	public ShortTermReport(String url, Date date) {
		this.url = url;
		this.date = date;
	}

	/** full constructor */
	public ShortTermReport(String stuId, ShortTermProject shortTermProject,
			String url, Date date, Set shortTermComments) {
		this.stuId = stuId;
		this.shortTermProject = shortTermProject;
		this.url = url;
		this.date = date;
		this.shortTermComments = shortTermComments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
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

	
	public ShortTermProject getShortTermProject() {
		return shortTermProject;
	}
	public void setShortTermProject(ShortTermProject shortTermProject) {
		this.shortTermProject = shortTermProject;
	}
	public Set getShortTermComments() {
		return this.shortTermComments;
	}

	public void setShortTermComments(Set shortTermComments) {
		this.shortTermComments = shortTermComments;
	}
	public float getAverageGrade(){
		Iterator it = this.shortTermComments.iterator();
		float avg,sum = 0;
		int i = 0;
		while (it.hasNext()) {  
		   sum += ((ShortTermComment)it.next()).getGrade();  
		   i++;
		}  
		avg = sum/i;
		return avg;
	}
}