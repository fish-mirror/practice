package com.zjicm.service;

import java.util.List;

import com.zjicm.entity.Resume;

public interface IResumeService {
	
	public void add(Resume r);
	public void update(Resume r);
	public Resume getResume(int id);
	public List<Resume> getResumeList(String stuId);
	public void delete(int id);
}
