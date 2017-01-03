package com.zjicm.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.Company;
import com.zjicm.company.service.ICompanyService;

public class CompanyListAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private ICompanyService cs;
	private List<Company> list;
	
	@Override
	public String execute() throws Exception {
		String institute = (String) request.getSession().getAttribute("institute");
		String flag = request.getParameter("flag");
		if(flag == null){
			list = cs.getCompanyList();
		}else{
			list = cs.getCompanyList(institute);
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


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public List<Company> getList() {
		return list;
	}

	public void setList(List<Company> list) {
		this.list = list;
	}


	
}
