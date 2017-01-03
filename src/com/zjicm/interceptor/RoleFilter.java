package com.zjicm.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zjicm.entity.User;
import org.apache.struts2.ServletActionContext;

public class RoleFilter implements Filter{
//	private ConcurrentHashMap<String, ClientUserSessionEntity> userSessionEntityMap = SessionUtil.getClientUserSessionMap();

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
    	HttpServletResponse response = (HttpServletResponse)res;
    	HttpSession session = request.getSession();
    	String info;
		String path = request.getRequestURI();
		try{
			if(path.endsWith("practice/index.jsp")||path.endsWith("/register.jsp")||path.endsWith("/login.jsp")||path.endsWith("/error.jsp")){
	            chain.doFilter(request, response);
	        }else{
			
				User u = (User)session.getAttribute("user");
				if(u!=null){
					if(path.startsWith("/practice/college/")){
						if(u.getRoleId() == 1){
							chain.doFilter(request, response);
						}else{
							info = "权限不符！";
							response.sendRedirect(request.getContextPath()+"/error.jsp?info="+response.encodeURL(info));
						}
					}else if(path.startsWith("/practice/student/")){
						if(u.getRoleId() == 2){
							chain.doFilter(request, response);
						}else{
							info = "权限不符！";
							response.sendRedirect(request.getContextPath()+"/error.jsp?info="+response.encodeURL(info));
						}
					}else{
						if(u.getRoleId() == 3){
							chain.doFilter(request, response);
						}else{
							info = "权限不符！";
							response.sendRedirect(request.getContextPath()+"/error.jsp?info="+response.encodeURL(info));
						}
					}
					
				}else{
					info = "请登录";
					request.getRequestDispatcher(request.getContextPath()+"/login.jsp?info="+response.encodeURL(info));
				}
	        }
	    }catch(Exception e){
				e.printStackTrace();
				info = "发生错误，请重试";
				request.setAttribute("info", info);
				request.getRequestDispatcher(request.getContextPath()+"/error.jsp?info="+response.encodeURL(info));
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	

}
