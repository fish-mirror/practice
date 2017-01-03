package com.zjicm.college.service;


import com.zjicm.dto.Page;
import com.zjicm.entity.College;

public interface ICollegeService {

	
	/**
	 * 查看院系用户的分页信息【管理员页面需要用到】
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Page pageForCollege(int pageSize, int page);

	//更新院系用户的照片
	public boolean updateColImg(String id,String url);
	
	//更新院系用户信息
	public void updateCol(College col);
	
	//获得一个院系用户的信息
	public College getCol(String id);
}
