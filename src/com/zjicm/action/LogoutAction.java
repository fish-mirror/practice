package com.zjicm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zjicm.util.SessionUtil;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements ServletRequestAware{
	
	
	String sessionID;
	HttpServletResponse response;
	HttpServletRequest request;
	
	
	public String execute() throws Exception{
		
		HttpSession session = request.getSession();
		sessionID = session.getId();
		
		SessionUtil.logOff(sessionID);
		session.removeAttribute("user");
		session.removeAttribute("name");
		session.removeAttribute("institute");
		
		return SUCCESS;
		
	}
	
	

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
}
