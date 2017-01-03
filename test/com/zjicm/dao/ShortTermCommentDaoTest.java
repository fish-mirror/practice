package com.zjicm.dao;

import java.util.List;

import com.zjicm.shortterm.dao.ShortTermCommentDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.zjicm.entity.ShortTermComment;
import com.zjicm.entity.User;

@ContextConfiguration("classpath:beans.xml")
public class ShortTermCommentDaoTest extends   
	AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired  
	private ShortTermCommentDao stcDao;
	
	@Test  
	public void addTest(){
		ShortTermComment stc = new ShortTermComment(2,new User("111"),"130806228", 9, "不错");
		stcDao.add(stc);
		List l = stcDao.getList("111");
		float grade = stcDao.getAverageGrade(2);
		System.out.println("====");
	}
	public void updateTest(){
		
	}
	public void updateStatusTest(){
		
	}
	
	
	
	
	public void getListTest(){
		
	}
	
}
