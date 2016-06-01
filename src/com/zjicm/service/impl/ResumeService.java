package com.zjicm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zjicm.dao.ResumeDao;
import com.zjicm.entity.Resume;
import com.zjicm.service.IResumeService;

@Component
public class ResumeService implements IResumeService {

	ResumeDao resumeDao;
	
	public ResumeDao getResumeDao() {
		return resumeDao;
	}
	@Resource
	public void setResumeDao(ResumeDao resumeDao) {
		this.resumeDao = resumeDao;
	}

	@Override
	public void add(Resume r) {
		resumeDao.save(r);

	}

	@Override
	public void update(Resume r) {
		resumeDao.update(r);
	}

	@Override
	public Resume getResume(int id) {
		
		return resumeDao.get(id);
	}

	@Override
	public List<Resume> getResumeList(String stuId) {		
		return resumeDao.getList(stuId);
	}
	@Override
	public void delete(int id) {
		resumeDao.delete(id);
	}

}
