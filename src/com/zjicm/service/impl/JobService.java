package com.zjicm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import java.util.List;

import com.zjicm.dao.JobDao;
import com.zjicm.dto.Page;
import com.zjicm.entity.Job;
import com.zjicm.service.IJobService;

@Component
public class JobService implements IJobService {

	JobDao jobDao;
	
	public JobDao getJobDao() {
		return jobDao;
	}
	@Resource
	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}

	@Override
	public void add(Job j) {
		jobDao.save(j);
	}
	
	@Override
	public Job get(Integer id) {
		return jobDao.get(id);
	}
	@Override
	public void update(Job j) {
		jobDao.update(j);
	}
	
	@Override
	public Page pageForList(int pageSize, int page) {
		int count = jobDao.count();
		int totalPage = Page.countTotalPage(pageSize, count);
		int offset = Page.countOffset(pageSize, page);
		int length = pageSize;
		int currentPage = Page.countCurrentPage(page);
		
		
		List<Job> dataList = jobDao.list(offset, length);
		Page p = new Page();
		p.setPageSize(pageSize);
		p.setCurrentPage(currentPage);
		p.setAllRow(count);
		p.setTotalPage(totalPage);
		p.setList(dataList);
		p.init();
		
		return p;
	}
	@Override
	public Page pageForListByCom(String comId, int pageSize, int page) {
		int count = jobDao.countByCom(comId);
		int totalPage = Page.countTotalPage(pageSize, count);
		int offset = Page.countOffset(pageSize, page);
		int length = pageSize;
		int currentPage = Page.countCurrentPage(page);
		
		List<Job> dataList = jobDao.listByCom(comId, offset, length);
		Page p = new Page();
		p.setPageSize(pageSize);
		p.setCurrentPage(currentPage);
		p.setAllRow(count);
		p.setTotalPage(totalPage);
		p.setList(dataList);
		p.init();
		
		return p;
	}
	@Override
	public Page pageForListStatus(int pageSize, int page) {
		int count = jobDao.countStatus();
		int totalPage = Page.countTotalPage(pageSize, count);
		int offset = Page.countOffset(pageSize, page);
		int length = pageSize;
		int currentPage = Page.countCurrentPage(page);
		
		List<Job> dataList = jobDao.listStatus(offset, length);
		Page p = new Page();
		p.setPageSize(pageSize);
		p.setCurrentPage(currentPage);
		p.setAllRow(count);
		p.setTotalPage(totalPage);
		p.setList(dataList);
		p.init();
		
		return p;
	}
	@Override
	public Page pageForListByComStatus(String comId, int pageSize, int page) {
		int count = jobDao.countByComStatus(comId);
		int totalPage = Page.countTotalPage(pageSize, count);
		int offset = Page.countOffset(pageSize, page);
		int length = pageSize;
		int currentPage = Page.countCurrentPage(page);
		
		List<Job> dataList = jobDao.listByComStatus(comId, offset, length);
		Page p = new Page();
		p.setPageSize(pageSize);
		p.setCurrentPage(currentPage);
		p.setAllRow(count);
		p.setTotalPage(totalPage);
		p.setList(dataList);
		p.init();
		
		return p;
	}
}
