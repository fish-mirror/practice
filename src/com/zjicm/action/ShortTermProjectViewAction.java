package com.zjicm.action;

import javax.annotation.Resource;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.ShortTermProject;
import com.zjicm.service.IShortTermService;

public class ShortTermProjectViewAction extends ActionSupport{
	private IShortTermService sts;
	private Integer id;
	ShortTermProject shortTerm ;
	@Override
	public String execute() throws Exception {
		
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
	
	@JSON(serialize=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public ShortTermProject getShortTerm() {
		return shortTerm;
	}
	public void setShortTerm(ShortTermProject shortTerm) {
		this.shortTerm = shortTerm;
	}
	

	
}
