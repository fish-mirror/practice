package com.zjicm.student.service;

import java.util.List;
import java.util.Map;

import com.zjicm.dto.Page;
import com.zjicm.dto.StatusDTO;
import com.zjicm.entity.Student;
public interface IStudentInfoService {

	
	/**
	 * 查看学生的分页信息
	 * @param graduate
	 * @param classname
	 * @param num
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Page pageForStudentInfo(Short graduate,String classname,String num,int pageSize,int page);
	/**
	 * 获得该学院的班级列表
	 * @param institute
	 * @return
	 */
	public List<String> getClassList(String institute);
	public List<String> getMajorList(String institute);
	
	//获得学院的状态分布
	public Map<String,StatusDTO> getStatus(String institute);
	
	//更新学生个人信息
	public boolean updateStuImg(String id,String url);
	
	//更新学生信息
	public void updateStu(Student stu);
	
	//获得一个学生信息
	public Student getStu(String id);
}
