package com.zjicm.student.service;

import java.util.List;

import com.zjicm.entity.Intention;

public interface IIntentionService {
	
	public void add(Intention i);
	public void update(Intention i);
	public Intention getIntention(int id);
	public List<Intention> getIntentionList(String institute);
	public void delete(int id);
}
