package com.zjicm.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.zjicm.entity.Student;
import com.zjicm.service.impl.StudentInfoService;

@ContextConfiguration("classpath:beans.xml")
public class StudentInfoServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	StudentInfoService sis;

	public StudentInfoService getSis() {
		return sis;
	}

	@Resource(name="studentInfoService")
	public void setSis(StudentInfoService sis) {
		this.sis = sis;
	}
/*	@Test
	public void pageForStudentInfoTest(){
		Page p = sis.pageForStudentInfo(null,"13网工1班",null,20, 1);
		System.out.println("===");
	}*/
/*	@Test
	public void getClassListTest(){
		List<String> list = sis.getClassList("新媒体学院");
		System.out.println("====");
	}*/
/*	@Test
	public void getStatusTest(){
		Map<String,StatusDTO> list = sis.getStatus("新媒体学院");
		System.out.println("====");
	}*/
	/*@Test
	public void updateStuImgTest(){
		Boolean b = sis.updateStuImg("130806228", "/practice/image/130806228.jpg");
		System.out.println("====");
	}*/
	@Test
	public void updateStuTest(){
		Student stu = sis.getStu("130806228");
		stu.setTel("1377842972");
		sis.updateStu(stu);
		System.out.println("====");
	}
	
}
