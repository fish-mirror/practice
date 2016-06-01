package com.zjicm.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.Student;
import com.zjicm.service.IStudentInfoService;
import com.zjicm.util.DateUtils;

public class UpdateStuInfo extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	
	private IStudentInfoService sis;
	
	private String info;
	
	@Override
	public String execute() throws Exception {
		try{
			String id = request.getParameter("id");
			String birth = request.getParameter("birth");
			String nation = request.getParameter("nation");
			Double height = Double.valueOf(request.getParameter("height"));
			Double weight = Double.valueOf(request.getParameter("weight"));
			String tel = request.getParameter("tel");
			String politics = request.getParameter("politics");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			
			Student stu = sis.getStu(id);
			
			stu.setBirth(DateUtils.timeStrToDate(birth));
			
			stu.setNation(nation);
			stu.setEmail(email);
			stu.setTel(tel);
			stu.setAddress(address);
			stu.setHeight(height);
			stu.setWeight(weight);
			stu.setPolitics(politics);
			
			sis.updateStu(stu);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			info = "提交失败，请重试！";
			return ERROR;
		}
		
		
		
	}
	
	
	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public IStudentInfoService getSis() {
		return sis;
	}

	@Resource(name="studentInfoService")
	public void setSis(IStudentInfoService sis) {
		this.sis = sis;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
