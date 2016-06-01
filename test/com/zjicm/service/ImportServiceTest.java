package com.zjicm.service;

import java.io.File;

import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.junit.Test;  

import com.zjicm.entity.User;
import com.zjicm.service.impl.ImportService;


@ContextConfiguration("classpath:beans.xml")
public class ImportServiceTest extends   
	AbstractTransactionalJUnit4SpringContextTests {  //让TestContext 测试框架可以在 JUnit 4.4 测试框架基础上运行起来
 
	@Autowired  
	private ImportService is;
	
	@Test  
	public void insertShortTermSelectedTest(){  

		File file = new File(this.getClass().getClassLoader().getResource("3.xls").getPath());
	   
		System.out.println(is.insertShortTermSelected(file,"新媒体学院"));
	}  
}  