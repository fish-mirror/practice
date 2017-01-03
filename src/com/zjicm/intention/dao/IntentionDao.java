package com.zjicm.intention.dao;

import java.util.List;

import com.zjicm.entity.Intention;

public interface IntentionDao {

	public void save(Intention i);
	public void update(Intention i);
	public Intention get(int id);
	public List<Intention> getList(String institute);
	public void delete(int id);
	
}
