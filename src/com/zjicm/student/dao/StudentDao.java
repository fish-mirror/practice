package com.zjicm.student.dao;

import java.util.List;

import com.zjicm.entity.Student;

public interface StudentDao {
	public void save(Student stu);
	public Student get(String id);
	
	//更新学生信息
	public void update(Student stu);
	public void updateImgUrl(String id,String url);
	
	public List<Student> find(int offset,int length);
	public List<Student> findByClassName(String classname,int offset,int length);
	public List<Student> findGraduateClass(short graduate,int offset,int length);

	public int count();
	public int countByClassName(String classname);
	public int countGraduateClass(short graduate);
	
	public List<String> getClassList(String institute);
	public List<String> getMajorList(String institute);
	//状态数量的查询
	/**
	 * 按是否毕业班来查询各个状态下的人数
	 * @param graduate
	 * @param status
	 * @return
	 */
	public List countGraduateStatus(short graduate);
	
	/**
	 * 按班级名查询各个实习状态下的人数
	 * @param classname
	 * @param status
	 * @return
	 */
	public List countClassStatus(String classname);
	/**
	 * 按学院查询各个实习状态下的人数
	 * @param institute
	 * @param status
	 * @return
	 */
	public List countInstituteStatus(String institute);
	
	//具体状态下的学生信息查询
	public List<Student> findInGraduateStatus(short graduate,short status);
	public List<Student> findInClassStatus(String classname,short status);
	public List<Student> findInInstituteStatus(String institute,short status);
	
	
}
	
