package com.zjicm.service;

import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.junit.Test;  

import com.zjicm.entity.User;


@ContextConfiguration("classpath:beans.xml")
public class LoginServiceTest extends   
	AbstractTransactionalJUnit4SpringContextTests {  //让TestContext 测试框架可以在 JUnit 4.4 测试框架基础上运行起来
 
	@Autowired  
	private ILoginService ls;
	
	@Test  
	public void handleUserLogin(){  
	   User user = new User();  
	   user.setId("s"); 
	   user.setPwd("0");
	   ls.login(user);
	}  
}  