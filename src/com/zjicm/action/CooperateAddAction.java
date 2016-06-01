package com.zjicm.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.service.ICompanyService;

public class CooperateAddAction extends ActionSupport{

	private ICompanyService cs;
	private String institute;
	private String com_id;
	private String result;
	
	@Override
	public String execute() throws Exception {
		try{
			cs.addCoop(institute,com_id);
			result = "success";
		}catch(Exception e){
			e.printStackTrace();
			result = "error";
		}
		return SUCCESS;
		
	}
	
	
	@JSON(serialize=false)
	public ICompanyService getCs() {
		return cs;
	}
	@Resource(name="companyService")
	public void setCs(ICompanyService cs) {
		this.cs = cs;
	}

	public String getResult() {
		return result;
	}


	@JSON(serialize=false)
	public String getInstitute() {
		return institute;
	}


	public void setInstitute(String institute) {
		this.institute = institute;
	}

	@JSON(serialize=false)
	public String getCom_id() {
		return com_id;
	}


	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}


	public void setResult(String result) {
		this.result = result;
	}
	
}
