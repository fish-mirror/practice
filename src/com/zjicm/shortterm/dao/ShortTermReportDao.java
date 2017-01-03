package com.zjicm.shortterm.dao;

import java.util.List;

import com.zjicm.entity.ShortTermReport;
import com.zjicm.dto.StudentShortTermReport;
import com.zjicm.entity.ShortTermProject;

public interface ShortTermReportDao {

	public void add(String stuId,  ShortTermProject shortTermProject);
	
	public void update(Integer id,String url);
	
	public void delete(Integer id);
	
	public void delete(ShortTermReport str);
	
	public ShortTermReport get(Integer id);
	public ShortTermReport get(String stuId, String term);
	public ShortTermReport get(String stuId, Integer pid);
	
	public List<StudentShortTermReport> getList(ShortTermProject shortTermProject);
	public List<ShortTermReport> getListByStu(String stuId);
	public List<StudentShortTermReport> getListByInstitute(String institute);
	public List<StudentShortTermReport> getListByCompany(String comId);
	//学院查看没有提交作品的列表
	public List<StudentShortTermReport> getNoUrlListByInstitute(String institute);
	//企业查看没有提交作品的列表
	public List<StudentShortTermReport> getNoUrlListByCompany(String comId);
	
}
