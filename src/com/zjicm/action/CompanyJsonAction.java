package com.zjicm.action;

import javax.annotation.Resource;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.Company;
import com.zjicm.service.ICompanyService;

public class CompanyJsonAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4440002543772750794L;
	
	private ICompanyService cs;
	private Company company;
	private String id;
	
	@Override
	public String execute() throws Exception {
		company = cs.getCompany(id);
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


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	
	
}
