package com.zjicm.dao;

import java.util.List;

import com.zjicm.entity.ShortTermProject;

public interface ShortTermProjectDao {

	public Integer add(ShortTermProject stp);
	public void update(ShortTermProject stp);
	public void updateStatus(Integer id, short status);
	public void updateUrl(Integer id, String url);
	
	public ShortTermProject getId(String name);
	public ShortTermProject get(Integer id);
	public List<ShortTermProject> getList(Short status, String institute, String comId, String term);
	
}
