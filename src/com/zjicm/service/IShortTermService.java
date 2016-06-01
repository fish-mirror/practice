package com.zjicm.service;

import java.util.List;

import com.zjicm.entity.ShortTermComment;
import com.zjicm.entity.ShortTermProject;
import com.zjicm.entity.ShortTermReport;

public interface IShortTermService {

	//添加短学期项目
	public Integer addProject(ShortTermProject stp);
	
	//更改短学期项目状态
	public void updateProjectStatus(Integer id, Short status);
	//更新项目
	public void updateProject(ShortTermProject stp);
	//获得一个项目对象
	public ShortTermProject getProject(Integer id);
	
	//查看短学期项目列表
	public List<ShortTermProject> getProjectList();
	public List<ShortTermProject> getProjectList(String term);
	public List<ShortTermProject> getProjectList(Short status);
	public List<ShortTermProject> getProjectListByCol(String institute,Short status);
	public List<ShortTermProject> getProjectListByCom(String comId,Short status);
	
	//学生选短学期项目
	public String addSelectProject(Integer pid, String stuId, String major,String insitute, String term);
	//取消选择【仅学生本人，所在院系的院系用户】
	public boolean cancelSelectedProject(Integer id, String stuId, String term, String major);
	//查看选课记录
	public ShortTermReport getReport(Integer id);
	public ShortTermReport getProjectSelected(String stuId, String term);
	//查看报告列表
	public List<ShortTermReport> getReportList(String stuId);
	public List<ShortTermReport> getReportList(String institute,String comId);
	
	//评分
	public void addCommemt(Integer rid,String userId,float grade);
	public void updateComment(Integer id, float grade);
	public Float getGrade(Integer rid);
	public List<ShortTermComment> getGradeList(Integer rid);
	
}
