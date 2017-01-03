package com.zjicm.cooperation.dao;

import com.zjicm.entity.Cooperate;

public interface CooperateDao {
	public void save(Cooperate coop);
	public Cooperate get(Integer id);
	public Cooperate get(String institute,String comId);
	public void delete(Cooperate coop);
	public void delete(String institute,String comId);
	
}
	
