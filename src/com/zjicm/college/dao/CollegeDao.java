package com.zjicm.college.dao;

import java.util.List;

import com.zjicm.entity.College;

public interface CollegeDao {
	public void save(College col);
	public College get(String id);
	
	//更新院系用户信息
	public void update(College col);
	public void updateImgUrl(String id,String url);
	
	public List<College> find(int offset,int length);

	public int count();
}
	
