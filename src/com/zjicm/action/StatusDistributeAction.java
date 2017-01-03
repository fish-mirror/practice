package com.zjicm.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zjicm.dto.StatusDTO;
import com.zjicm.service.impl.StudentInfoService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class StatusDistributeAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private StudentInfoService sis;
	private Map<String,StatusDTO> map;
	
	@Override
	public String execute() throws Exception {
		String institute = request.getParameter("institute");
		map = sis.getStatus(institute);
		return SUCCESS;
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

	public Map<String, StatusDTO> getMap() {
		return map;
	}

	public void setMap(Map<String, StatusDTO> map) {
		this.map = map;
	}

	

	

	
}
