package com.zjicm.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.Intention;
import com.zjicm.service.impl.IntentionService;

public class IntentionSaveAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private IntentionService is;
	private Integer id;
	private String info;
	
	@Override
	public String execute() throws Exception {
		String tittle = request.getParameter("tittle");
		String content = request.getParameter("content");
		String colId = request.getParameter("col_id");
		String institute = request.getParameter("institute");
		try{
			if(id!=null){
				Intention i = is.getIntention(id);
				i.setContent(content);
				i.setTittle(tittle);
				is.update(i);
			}else{
				Intention i = new Intention(colId,institute,tittle,content,new Date());
				is.add(i);
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			request.getParameter("tittle");
			info = "保存失败！请重试。";
			return ERROR;
		}
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
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getInfo() {
		return info;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
