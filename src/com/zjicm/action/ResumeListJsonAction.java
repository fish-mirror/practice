package com.zjicm.action;

import java.util.List;

import javax.annotation.Resource;

import com.zjicm.entity.Resume;
import com.zjicm.service.impl.ResumeService;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class ResumeListJsonAction extends ActionSupport{

	private ResumeService rs;
	private List<Resume> resumeList;
	private String stuId;
	
	@Override
	public String execute() throws Exception {
		resumeList = rs.getResumeList(stuId);
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


	public List<Resume> getResumeList() {
		return resumeList;
	}


	public void setResumeList(List<Resume> resumeList) {
		this.resumeList = resumeList;
	}

	@JSON(serialize=false)
	public String getStuId() {
		return stuId;
	}


	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	


	
	
	
}
