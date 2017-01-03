package com.zjicm.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zjicm.entity.Resume;
import com.zjicm.student.service.ResumeService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class ResumeSaveAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private ResumeService rs;
	private Integer resume_id;
	private String info;
	
	@Override
	public String execute() throws Exception {
		String tittle = request.getParameter("tittle");
		String majorClass = request.getParameter("major_class");
		String schoolExp = request.getParameter("school_exp");
		String practiceExp = request.getParameter("practice_exp");
		String certificate = request.getParameter("certificate");
		String selfComment = request.getParameter("self_comment");
		try{
			if(resume_id!=null){
				Resume r = rs.getResume(resume_id);
				r.setCertificate(certificate);
				r.setMajorClass(majorClass);
				r.setPracticeExp(practiceExp);
				r.setSchoolExp(schoolExp);
				r.setSelfComment(selfComment);
				rs.update(r);
			}else{
				String stuId = request.getParameter("id");
				Resume r = new Resume(stuId,tittle,majorClass,schoolExp,practiceExp,certificate,selfComment);
				rs.add(r);
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			info = "保存失败！请重试。";
			return ERROR;
		}
	}
	
	
	@JSON(serialize=false)
	public ResumeService getRs() {
		return rs;
	}
	@Resource(name="resumeService")
	public void setRs(ResumeService rs) {
		this.rs = rs;
	}

	
	

	public Integer getResume_id() {
		return resume_id;
	}


	public void setResume_id(Integer resume_id) {
		this.resume_id = resume_id;
	}


	public String getInfo() {
		return info;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
