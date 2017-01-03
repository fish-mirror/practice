package com.zjicm.practice.service;

import java.util.List;

import javax.annotation.Resource;

import com.zjicm.practice.dao.PracticeDataDao;
import org.springframework.stereotype.Component;

import com.zjicm.dto.Page;
import com.zjicm.entity.PracticeData;

@Component
public class PracticeDataService implements IPracticeDataService {

	PracticeDataDao practiceDataDao;

	public PracticeDataDao getPeracticeDataDao() {
		return practiceDataDao;
	}
	@Resource
	public void setPracticeDataDao(PracticeDataDao practiceDataDao) {
		this.practiceDataDao = practiceDataDao;
	}

	@Override
	public Page pageForPracticeData(int pageSize, int page) {
		int count = this.practiceDataDao.count();
		int totalPage =Page.countTotalPage(pageSize, count);
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
