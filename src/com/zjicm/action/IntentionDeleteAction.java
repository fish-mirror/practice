package com.zjicm.action;

import javax.annotation.Resource;

import com.zjicm.service.impl.IntentionService;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class IntentionDeleteAction extends ActionSupport{

	private IntentionService is;
	private Integer intention_id;
	private String result;
	
	@Override
	public String execute() throws Exception {
		try{
			is.delete(intention_id);
			result = "success";
		}catch(Exception e){
			e.printStackTrace();
			result = "error";
		}
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

	@JSON(serialize=false)
	public Integer getIntention_id() {
		return intention_id;
	}


	public void setIntention_id(Integer intention_id) {
		this.intention_id = intention_id;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}
	
}
