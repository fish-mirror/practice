package com.zjicm.action;

import javax.annotation.Resource;

import com.zjicm.entity.Resume;
import com.zjicm.student.service.ResumeService;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class ResumeJsonAction extends ActionSupport{

	private ResumeService rs;
	private Resume resume;
	private Integer id;
	
	@Override
	public String execute() throws Exception {
		resume = rs.getResume(id);
		return SUCCESS;
	}
	
	
	@JSON(serialize=false)
	public ResumeService getRs() {
		return rs;
	}
	@Resource(name="resumeService")
	public void setRs(ResumeService rs) {
		this.rs = rs;
	}


	public Resume getResume() {
		return resume;
	}


	public void setResume(Resume resume) {
		this.resume = resume;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
