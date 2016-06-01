package com.zjicm.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.Intention;
import com.zjicm.service.impl.IntentionService;

public class IntentionListJsonAction extends ActionSupport{

	private IntentionService is;
	private List<Intention> intentionList;
	private String institute;
	
	@Override
	public String execute() throws Exception {
		intentionList = is.getIntentionList(institute);
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


	public List<Intention> getIntentionList() {
		return intentionList;
	}


	public void setIntentionList(List<Intention> intentionList) {
		this.intentionList = intentionList;
	}

	@JSON(serialize=false)
	public String getInstitute() {
		return institute;
	}


	public void setInstitute(String institute) {
		this.institute = institute;
	}

	
	
}
