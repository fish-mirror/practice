package com.zjicm.dao;

import java.util.List;

import com.zjicm.entity.Job;

public interface JobDao {

	public void save(Job j);
	public void update(Job j);
	public void updateStatus(Integer id,short status);
	public void addNum(Integer id,Integer haveNum);
	
	public Job get(Integer id);
	public List<Job> list(int offset, int length);
	public List<Job> listByCom(String comId, int offset, int length);
	//查看企业进行中的招聘
	public List<Job> listStatus(int offset, int length);
	public List<Job> listByComStatus(String comId, int offset, int length);
	
	public int count();
	public int countByCom(String comId);
	public int countStatus();
	public int countByComStatus(String comId);
	
	
}
