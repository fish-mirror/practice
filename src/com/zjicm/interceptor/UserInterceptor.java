package com.zjicm.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zjicm.util.SessionUtil;

public class UserInterceptor extends AbstractInterceptor{
	
	private String info;
	private HttpServletRequest request;
	

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext(); 
		request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
		String sessionID = request.getSession().getId();
		String result = SessionUtil.checkSession(sessionID);
		switch(result){
			case "login":
				info = "请登录后再操作";
				return"login";
			case "relogin":
				info = "登录超时，请重新登录";
				return"login";
			case "error":
				info = "遇到一个错误，请重新登录";
				return"login";
			case "success":
				return invocation.invoke();
			default:
				return"login";
		}
		
		
	}

	

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}



}
