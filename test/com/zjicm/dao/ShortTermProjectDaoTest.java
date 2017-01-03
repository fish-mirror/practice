package com.zjicm.dao;

import java.util.List;

import com.zjicm.shortterm.dao.ShortTermProjectDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.zjicm.entity.ShortTermProject;

@ContextConfiguration("classpath:beans.xml")
public class ShortTermProjectDaoTest extends   
	AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired  
	private ShortTermProjectDao stpDao;
	
	@Test  
	public void addTest(){
		ShortTermProject stp = new ShortTermProject("电子商务", "新媒体学院", (short) 0);
		stpDao.add(stp);
		List l = stpDao.getList(null, "新媒体学院", null, null);
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
