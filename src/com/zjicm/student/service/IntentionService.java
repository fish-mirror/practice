package com.zjicm.student.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zjicm.intention.dao.IntentionDao;
import com.zjicm.entity.Intention;

@Component
public class IntentionService implements IIntentionService {

	IntentionDao resumeDao;
	
	public IntentionDao getIntentionDao() {
		return resumeDao;
	}
	@Resource
	public void setIntentionDao(IntentionDao resumeDao) {
		this.resumeDao = resumeDao;
	}

	@Override
	public void add(Intention i) {
		resumeDao.save(i);

	}

	@Override
	public void update(Intention i) {
		resumeDao.update(i);
	}

	@Override
	public Intention getIntention(int id) {
		
		return resumeDao.get(id);
	}

	@Override
	public List<Intention> getIntentionList(String institute) {		
		return resumeDao.getList(institute);
	}
	@Override
	public void delete(int id) {
		resumeDao.delete(id);
	}

}
