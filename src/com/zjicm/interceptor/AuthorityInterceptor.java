package com.zjicm.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zjicm.util.SessionUtil;

public class AuthorityInterceptor extends AbstractInterceptor implements ServletRequestAware{
	
	private String info;
	private HttpServletRequest request;
	

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String sessionID = request.getSession().getId();
		String result = SessionUtil.checkSession(sessionID);
		switch(result){
			case "login":
				//重定向到登录页面
				return"login";
			case "success":
				return invocation.invoke();
			default:return"login";
		}
		
		
	}

	

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
