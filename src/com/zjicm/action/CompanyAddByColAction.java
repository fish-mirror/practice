package com.zjicm.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zjicm.entity.User;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.Company;
import com.zjicm.company.service.CompanyService;

public class CompanyAddByColAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private CompanyService cs;
	private String info;
	
	@Override
	public String execute() throws Exception {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		
		String institute = request.getParameter("institute");
		
		User u = new User(id, pwd, (short)3);
		Company com = new Company(id, name);
		com.setType("");
		try{
			cs.saveAndCoop(u, com, institute);
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			info = "创建失败，请重试";
			return ERROR;
		}
		
	}
	
	
	@JSON(serialize=false)
	public CompanyService getCs() {
		return cs;
	}
	@Resource(name="companyService")
	public void setCs(CompanyService cs) {
		this.cs = cs;
	}

	public String getInfo() {
		return info;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
