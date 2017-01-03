package com.zjicm.service;


import java.util.List;

import com.zjicm.dto.Page;
import com.zjicm.entity.Company;
import com.zjicm.entity.User;

public interface ICompanyService {

	public void save(User u, Company com);
	
	public void saveAndCoop(User u ,Company com, String colId);

	public List<Company> getCompanyList();
	public List<Company> getCompanyList(String colId);
	public Page pageForCompany(int pageSize, int page);
	public Page pageForCompany(String colId, int pageSize,int page);

	//更新企业用户的照片
	public boolean updateComImg(String id,String url);
	
	//更新企业用户信息
	public void updateCompany(Company com);
	
	//获得一个企业用户的信息
	public Company getCompany(String id);
	
	public void deleteCoop(Integer coopId);
	public void deleteCoop(String colId, String comId);
	
	public void addCoop(String colId, String comId);
}
