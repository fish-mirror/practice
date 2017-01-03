package com.zjicm.dao;

import com.zjicm.job.dao.JobDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.zjicm.entity.Company;
import com.zjicm.entity.Job;

@ContextConfiguration("classpath:beans.xml")
public class JobDaoTest extends   
	AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired  
	private JobDao jobDao;
	
	@Test  
	public void saveTest(){
		Job j = new Job();
		j.setName("name");
		j.setDesc("name");
		j.setNeed("name");
		j.setTime("name");
		j.setProvince("name");
		j.setCity("name");
		j.setPlace("name");
		j.setNeedNum(Integer.valueOf("10"));
		String uid = "youcai";
		j.setCompany(new Company(uid));
		jobDao.save(j);
		System.out.println("====");
	}
/*	public void updateTest(){
		ShortTermProject stp = stpDao.get(2);
		stpDao.updateStatus(2,(short)1);
		ShortTermProject stp2 = stpDao.get(2);
		System.out.println("====");
		
	}*/
	public void updateStatusTest(){
		
	}
	
	
	
	
	public void getListTest(){
		
	}
	
}
