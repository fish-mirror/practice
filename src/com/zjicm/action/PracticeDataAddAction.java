package com.zjicm.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zjicm.dto.StudentDTO;
import com.zjicm.entity.PracticeData;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.service.IPracticeDataService;

public class PracticeDataAddAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private IPracticeDataService pds;
	
	
	
	@Override
	public String execute() throws Exception {
		String tittle = request.getParameter("tittle");
		String content = request.getParameter("content");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String stuId = (String) request.getSession().getAttribute("ID");
		
		
		
		try{
			PracticeData pd = new PracticeData(new StudentDTO(stuId), content, province, city, new Date());
			pds.add(pd);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	
	public IPracticeDataService getPds() {
		return pds;
	}
	@Resource
	public void setPds(IPracticeDataService pds) {
		this.pds = pds;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
