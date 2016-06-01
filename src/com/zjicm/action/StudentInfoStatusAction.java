package com.zjicm.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.dto.Page;
import com.zjicm.service.impl.StudentInfoService;

public class StudentInfoStatusAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private Page p;
	private StudentInfoService sis;
	
	@Override
	public String execute() throws Exception {
		Short graduate = request.getParameter("graduate")==null ? null : Short.valueOf(request.getParameter("graduate"));
		String classname = request.getParameter("classname");
		String num = request.getParameter("num");
		Integer pageSize = request.getParameter("pageSize")==null ? 20 : Integer.valueOf(request.getParameter("pageSize"));
		Integer page = request.getParameter("page")==null ? 1 : Integer.valueOf(request.getParameter("page"));
		
		p = sis.pageForStudentInfo(graduate,classname,num,pageSize, page);

		
		return super.execute();
	}
	
	public Page getP() {
		return p;
	}

	public void setP(Page p) {
		this.p = p;
	}
	@JSON(serialize=false)
	public StudentInfoService getSis() {
		return sis;
	}
	@Resource(name="studentInfoService")
	public void setSis(StudentInfoService sis) {
		this.sis = sis;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	
}
