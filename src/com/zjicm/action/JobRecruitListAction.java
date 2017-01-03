package com.zjicm.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.dto.Page;
import com.zjicm.job.service.IJobService;

public class JobRecruitListAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private Page p;
	private IJobService js;
	
	@Override
	public String execute() throws Exception {
		
		Integer pageSize = request.getParameter("pageSize")==null ? 20 : Integer.valueOf(request.getParameter("pageSize"));
		Integer page = request.getParameter("page")==null ? 1 : Integer.valueOf(request.getParameter("page"));
		
		p = js.pageForListStatus(pageSize, page);

		
		return super.execute();
	}
	
	public Page getP() {
		return p;
	}

	public void setP(Page p) {
		this.p = p;
	}
	
	

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	@JSON(serialize=false)
	public IJobService getJs() {
		return js;
	}
	@Resource
	public void setJs(IJobService js) {
		this.js = js;
	}
	

	
}