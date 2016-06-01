package com.zjicm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zjicm.dao.PracticeDataDao;
import com.zjicm.dto.Page;
import com.zjicm.entity.PracticeData;
import com.zjicm.service.IPracticeDataService;

@Component
public class PracticeDataService implements IPracticeDataService {

	PracticeDataDao practiceDataDao;
	
	public PracticeDataDao getPracticeDataDao() {
		return practiceDataDao;
	}
	@Resource
	public void setPracticeDataDao(PracticeDataDao practiceDataDao) {
		this.practiceDataDao = practiceDataDao;
	}

	@Override
	public Page pageForPracticeData(int pageSize, int page) {
		int count = this.practiceDataDao.count();
		int totalPage = Page.countTotalPage(pageSize, count);
		int offset = Page.countOffset(pageSize, page);
		int length = pageSize;
		int currentPage = Page.countCurrentPage(page);
		
		List<PracticeData> dataList = this.practiceDataDao.getList(offset, length);
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
	public void add(PracticeData pd) {
		this.practiceDataDao.add(pd);
	}

}
