package com.zjicm.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zjicm.entity.User;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.Company;
import com.zjicm.service.impl.CompanyService;

public class CompanyRegisterAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private CompanyService cs;
	private String info;
	
	@Override
	public String execute() throws Exception {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		
		
		User u = new User(id, pwd, (short)3);
		Company com = new Company(id, name);
		try{
			cs.save(u,com);
			return "login";
		}catch(Exception e){
			e.printStackTrace();
			info = "注册失败，请重试";
			return "input";
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
