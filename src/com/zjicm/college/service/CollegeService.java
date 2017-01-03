package com.zjicm.college.service;

import java.util.List;
import javax.annotation.Resource;

import com.zjicm.college.dao.CollegeDao;
import com.zjicm.dto.Page;
import com.zjicm.entity.College;
import org.springframework.stereotype.Component;

@Component
public class CollegeService implements ICollegeService {

	CollegeDao collegeDao;

	public CollegeDao getCollegeDao() {
		return collegeDao;
	}
	@Resource
	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}

	@Override
	public Page pageForCollege(int pageSize, int page){

		int count = collegeDao.count();
		int totalPage = Page.countTotalPage(pageSize, count);
		int offset = Page.countOffset(pageSize, page);
		int length = pageSize;
		int currentPage = Page.countCurrentPage(page);
		
		List<College> list = collegeDao.find(offset, length);
		
		Page p = new Page();
		p.setPageSize(pageSize);
		p.setCurrentPage(currentPage);
		p.setAllRow(count);
		p.setTotalPage(totalPage);
		p.setList(list);
		p.init();
		
		return p;
	}

	@Override
	public boolean updateColImg(String id, String url) {
		try{
			collegeDao.updateImgUrl(id, url);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public void updateCol(College Col) {
		collegeDao.update(Col);
	}
	
	@Override
	public College getCol(String id) {
		return collegeDao.get(id);
	}
	
}
