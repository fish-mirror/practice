package com.zjicm.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration("classpath:beans.xml")
public class StudentDaoTest extends   
	AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired  
	private StudentDao studentDao;
	
	@Test  
	public void countGraduateStatus(){  

		List<Object[]> list = studentDao.countGraduateStatus((short)1);
		for(Object[] link : list){  
			  
		    short status = (short) link[0];  
		  
		    int s = ((Long) link[1]).intValue();  
		  
		    System.out.println("==========");
		}  
	}  
}
