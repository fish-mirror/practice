package com.zjicm.action;

import javax.annotation.Resource;

import com.zjicm.entity.Student;
import com.zjicm.service.IStudentInfoService;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class PersonalInfoAction extends ActionSupport{
	private IStudentInfoService sis;
	private String id;
	private Student stu;
	
	
	@Override
	public String execute() throws Exception {
		stu = sis.getStu(id);
		return SUCCESS;
	}

	@JSON(serialize=false)
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public Student getStu() {
		return stu;
	}


	public void setStu(Student stu) {
		this.stu = stu;
	}

	@JSON(serialize=false)
	public IStudentInfoService getSis() {
		return sis;
	}

	@Resource(name="studentInfoService")
	public void setSis(IStudentInfoService sis) {
		this.sis = sis;
	}

	
	

}
