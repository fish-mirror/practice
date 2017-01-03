package com.zjicm.job.service;

import com.zjicm.dto.Page;
import com.zjicm.entity.Job;

public interface IJobService {

	public Job get(Integer id);
	public void update(Job j);
	//发布职位
	public void add(Job j);
	//查看职位列表
	public Page pageForList(int pageSize, int page);
	//查看某用户发布的职位
	public Page pageForListByCom(String comId,int pageSize,int page);
	//查看进行中的职位
	public Page pageForListStatus(int pageSize,int page);
	//查看进行中的某用户发布的职位
	public Page pageForListByComStatus(String comId,int pageSize,int page);
	
}
