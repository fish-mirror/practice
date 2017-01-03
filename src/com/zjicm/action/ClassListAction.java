package com.zjicm.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zjicm.service.IStudentInfoService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class ClassListAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private IStudentInfoService sis;
	private List<String> list;
	
	@Override
	public String execute() throws Exception {
		String institute = (String) request.getSession().getAttribute("institute");
		list = sis.getClassList(institute);
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public IStudentInfoService getSis() {
		return sis;
	}
	@Resource(name="studentInfoService")
	public void setSis(IStudentInfoService sis) {
		this.sis = sis;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	
}
