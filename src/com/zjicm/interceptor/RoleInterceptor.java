package com.zjicm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zjicm.entity.User;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class RoleInterceptor extends AbstractInterceptor{
	
	private String info;
	private HttpServletRequest request;
	

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext(); 
		request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String path = request.getRequestURI();
		try{
			
			if(u!=null){
				if(path.startsWith("/practice/college/")){
					if(u.getRoleId() == 1){
						return invocation.invoke();
					}else{
						info = "权限不符！";
						return "role_error";
					}
				}else if(path.startsWith("/practice/student/")){
					if(u.getRoleId() == 2){
						return invocation.invoke();
					}else{
						info = "权限不符！";
						return "role_error";
					}
				}else{
					if(u.getRoleId() == 3){
						return invocation.invoke();
					}else{
						info = "权限不符！";
						return "role_error";
					}
				}
				
			}else{
				info = "请登录";
				request.setAttribute("info", info);
				return "login";
			}
	    }catch(Exception e){
				e.printStackTrace();
				info = "发生错误，请重试";
				return "error";
		}
	}
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
