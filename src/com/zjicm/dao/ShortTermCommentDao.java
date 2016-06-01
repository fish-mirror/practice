package com.zjicm.dao;

import java.util.List;

import com.zjicm.entity.ShortTermComment;

public interface ShortTermCommentDao {

	public void add(ShortTermComment stc);
	
	public void update(ShortTermComment stc);
	
	public void delete(Integer id);
	
	public ShortTermComment get(Integer id);
	
	public List<ShortTermComment> getList(Integer rid);
	public List<ShortTermComment> getList(String uid);
	
	public float getAverageGrade(Integer rid);
	
	
}
