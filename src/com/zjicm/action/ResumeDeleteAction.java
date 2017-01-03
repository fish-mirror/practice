package com.zjicm.action;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.service.impl.ResumeService;

public class ResumeDeleteAction extends ActionSupport{

	private ResumeService rs;
	private Integer resume_id;
	private String result;
	
	@Override
	public String execute() throws Exception {
		try{
			rs.delete(resume_id);
			result = "success";
		}catch(Exception e){
			e.printStackTrace();
			result = "error";
		}
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

	@JSON(serialize=false)
	public Integer getResume_id() {
		return resume_id;
	}


	public void setResume_id(Integer resume_id) {
		this.resume_id = resume_id;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}
	
}
