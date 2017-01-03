package com.zjicm.action;

import javax.annotation.Resource;

import com.zjicm.service.impl.IntentionService;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.Intention;

public class IntentionJsonAction extends ActionSupport{

	private IntentionService is;
	private Intention intention;
	private Integer id;
	
	@Override
	public String execute() throws Exception {
		intention = is.getIntention(id);
		return SUCCESS;
	}
	
	
	@JSON(serialize=false)
	public IntentionService getIs() {
		return is;
	}
	@Resource(name="intentionService")
	public void setIs(IntentionService is) {
		this.is = is;
	}


	public Intention getIntention() {
		return intention;
	}


	public void setIntention(Intention intention) {
		this.intention = intention;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
