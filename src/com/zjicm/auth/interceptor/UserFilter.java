package com.zjicm.auth.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zjicm.util.SessionUtil;
import org.apache.struts2.ServletActionContext;

public class UserFilter implements Filter{
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
		try{
			
	    	
	    	String path = request.getRequestURI();
	    	//如果是登录或者注册请求则不进行过滤
	    	if(path.endsWith("practice/index.jsp")||path.endsWith("/register.jsp")||path.endsWith("/login.jsp")){
	            chain.doFilter(request, response);
	        }else{
	        	//获取请求中携带的sessionId
				String sessionID = (String) session.getId();
				String result = SessionUtil.checkSession(sessionID);
				switch(result){
					case "login":
						response.sendRedirect(request.getContextPath()+"/login.jsp");
						break;
					case "relogin":
						response.sendRedirect(request.getContextPath()+"/login.jsp");
						break;
					case "success":
						chain.doFilter(request, response);
						break;
					case "error":
						info = "遇到一个错误，请重新登录";
						response.sendRedirect(request.getContextPath()+"/login.jsp?info="+response.encodeURL(info));
					default:break;
				}
	        }
	    }catch(Exception e){
				e.printStackTrace();
				info = "遇到一个错误，请重新登录";
				response.sendRedirect("/login.jsp?info="+response.encodeURL(info));
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	

}
