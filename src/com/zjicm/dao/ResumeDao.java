package com.zjicm.dao;

import java.util.List;

import com.zjicm.entity.Resume;

public interface ResumeDao {

	public void save(Resume r);
	public void update(Resume r);
	public Resume get(int id);
	public List<Resume> getList(String stuId);
	public void delete(int id);
	
}
