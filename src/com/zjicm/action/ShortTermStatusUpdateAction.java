package com.zjicm.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zjicm.shortterm.service.IShortTermService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.ShortTermProject;

public class ShortTermStatusUpdateAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private IShortTermService sts;
	private Integer id;
	private Short status;
	ShortTermProject shortTerm ;
	@Override
	public String execute() throws Exception {
		
		sts.updateProjectStatus(id, status);;
		shortTerm = sts.getProject(id);
		
		return SUCCESS;
	}
	@JSON(serialize=false)
	public IShortTermService getSts() {
		return sts;
	}
	@Resource(name="shortTermService")
	public void setSts(IShortTermService sts) {
		this.sts = sts;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	@JSON(serialize=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@JSON(serialize=false)
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public ShortTermProject getShortTerm() {
		return shortTerm;
	}
	public void setShortTerm(ShortTermProject shortTerm) {
		this.shortTerm = shortTerm;
	}
	

	
}
