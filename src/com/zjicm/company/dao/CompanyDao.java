package com.zjicm.company.dao;

import java.util.List;

import com.zjicm.entity.Company;
import com.zjicm.entity.User;

public interface CompanyDao {
	public void save(User u, Company com);
	public Company get(String id);
	public void update(Company com);
	public void updateImgUrl(String id,String url);
	
	public List<Company> find();
	public List<Company> find(String col);
	public List<Company> find(int offset,int length);
	public List<Company> find(String col, int offset,int length);
	
	public int count();
	public int count(String col);
}
	
