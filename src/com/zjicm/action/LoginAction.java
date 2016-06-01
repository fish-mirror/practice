package com.zjicm.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zjicm.dto.UserDTO;
import com.zjicm.entity.College;
import com.zjicm.entity.Company;
import com.zjicm.entity.Student;
import com.zjicm.entity.User;
import com.zjicm.service.ICollegeService;
import com.zjicm.service.ICompanyService;
import com.zjicm.service.ILoginService;
import com.zjicm.service.IStudentInfoService;
import com.zjicm.service.impl.LoginService;
import com.zjicm.service.impl.StudentInfoService;
import com.zjicm.util.SessionUtil;

public class LoginAction extends ActionSupport 
		implements ModelDriven<UserDTO>,ServletResponseAware,ServletRequestAware{
	
	ILoginService ls;
	IStudentInfoService sis;
	ICollegeService cs;
	ICompanyService coms;
	String sessionID;
	HttpServletResponse response;
	HttpServletRequest request;
	private UserDTO udto;
	private String info;
	
	
	public ILoginService getLs() {
		return ls;
	}
	@Resource
	public void setLs(ILoginService ls) {
		this.ls = ls;
	}
	public UserDTO getUdto() {
		return udto;
	}
	public void setUdto(UserDTO udto) {
		this.udto = udto;
	}
	//设置info属性只读
	public String getInfo() {
		return info;
	}
	public String execute() throws Exception{
		
		User u = new User(udto.getName(),udto.getPwd());
		User loginUser = ls.login(u);
		
		if(loginUser == null){
			info = "用户不存在或密码错误";
			return "login";
		}else{
			HttpSession session = request.getSession();
			sessionID = session.getId();
			session.setAttribute("user", loginUser);
			SessionUtil.loginSuccess(sessionID, u);
			short role = loginUser.getRoleId();
			switch(role){
				case 1:
					College c = cs.getCol(loginUser.getId());
					session.setAttribute("ID", c.getId());
					session.setAttribute("name",c.getName());
					session.setAttribute("institute", c.getInstitute());
					return "college";
				case 2:
					Student s = sis.getStu(loginUser.getId());
					session.setAttribute("ID",s.getId());
					session.setAttribute("name",s.getName());
					session.setAttribute("major",s.getMajor());
					session.setAttribute("institute", s.getInstitute());
					return "student";
				case 3:
					Company com = coms.getCompany(loginUser.getId());
					session.setAttribute("ID", com.getId());
					session.setAttribute("name",com.getName());
					return "company";
				default:return ERROR;
			}
		}
	}
	@Override
	public UserDTO getModel() {
		if(udto == null){
			udto = new UserDTO();
		}
		return udto;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	public IStudentInfoService getSis() {
		return sis;
	}
	@Resource(name="studentInfoService")
	public void setSis(IStudentInfoService sis) {
		this.sis = sis;
	}
	public ICollegeService getCs() {
		return cs;
	}
	@Resource(name="collegeService")
	public void setCs(ICollegeService cs) {
		this.cs = cs;
	}
	
	
	public ICompanyService getComs() {
		return coms;
	}
	@Resource(name="companyService")
	public void setComs(ICompanyService coms) {
		this.coms = coms;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
}
