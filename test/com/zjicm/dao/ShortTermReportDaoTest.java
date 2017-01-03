package com.zjicm.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.zjicm.entity.ShortTermReport;

@ContextConfiguration("classpath:beans.xml")
public class ShortTermReportDaoTest extends   
	AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired  
	private ShortTermReportDao strDao;
	
	/*@Test  
	public void addTest(){
		ShortTermProject stp = new ShortTermProject(2);
		strDao.add("130806228", stp);
		List l = strDao.getList(stp);
		System.out.println("====");
	}*/
	public void updateTest(){
		
	}
	public void updateStatusTest(){
		
	}
	@Test
	public void getTest(){
		ShortTermReport str = strDao.get("130806228", "2014-2015学年第二学期");
		System.out.println("====");
	}
	
	
	
	public void getListTest(){
		
	}
	
}
