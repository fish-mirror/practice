package com.zjicm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zjicm.dao.StudentDao;
import com.zjicm.dto.Page;
import com.zjicm.dto.StatusDTO;
import com.zjicm.entity.Student;
import com.zjicm.service.IStudentInfoService;
import org.springframework.stereotype.Component;

@Component
public class StudentInfoService implements IStudentInfoService {

	StudentDao studentDao;

	public StudentDao getStudentDao() {
		return studentDao;
	}
	@Resource
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	
	public Page pageForStudentInfo(Short graduate, String classname, String num, int pageSize, int page){

		int count;
		if(graduate!=null){
			count = studentDao.countGraduateClass(graduate);
		}else if(classname!=null){
			count = studentDao.countByClassName(classname);
		}else if(num!=null){
			count = 1;
		}else{
			count = studentDao.count();
		}
		int totalPage = Page.countTotalPage(pageSize, count);
		int offset = Page.countOffset(pageSize, page);
		int length = pageSize;
		int currentPage = Page.countCurrentPage(page);
		
		List<Student> list;
		if(graduate!=null){
			list = studentDao.findGraduateClass(graduate, offset, length);
		}else if(classname!=null){
			list = studentDao.findByClassName(classname, offset, length);
		}else if(num!=null){
			list = new ArrayList(0);
			list.add(studentDao.get(num));
		}else{
			list = studentDao.find(offset, length);
		}
		
		
		Page p = new Page();
		p.setPageSize(pageSize);
		p.setCurrentPage(currentPage);
		p.setAllRow(count);
		p.setTotalPage(totalPage);
		p.setList(list);
		p.init();
		
		return p;
	}
	
	public List<String> getClassList(String institute){
		return studentDao.getClassList(institute);
	}
	public List<String> getMajorList(String institute){
		return studentDao.getMajorList(institute);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, StatusDTO> getStatus(String institute) {
		List<Object[]> list;
		Map<String,StatusDTO> map = new HashMap<String, StatusDTO>(0);
		//学院的状态分布
		list = studentDao.countInstituteStatus(institute);
		map.put("institute", this.createStatusDTO(list));
		//毕业班的状态分布
		list = studentDao.countGraduateStatus((short)1);
		map.put("graduate",this.createStatusDTO(list));
		//非毕业班的状态分布
		list = studentDao.countGraduateStatus((short)0);
		map.put("non_graduate", this.createStatusDTO(list));
		//各个班级的状态分布
		List<String> classList = this.getClassList(institute);
		for(String classname : classList){
			list = studentDao.countClassStatus(classname);
			map.put(classname, this.createStatusDTO(list));
		}
		return map;
	}
	
	private StatusDTO createStatusDTO(List<Object[]> list){
		StatusDTO temp = new StatusDTO();
		for(Object[] o : list){  
			switch((short)o[0]){
				case 0:temp.setNo_practice(((Long)o[1]).intValue());break;
				case 1:temp.setPracticing(((Long)o[1]).intValue());break;
				case 2:temp.setPractice_graded(((Long)o[1]).intValue());break;
				case 3:temp.setPracticed(((Long)o[1]).intValue());break;
			}
		}
		return temp;
	}
	@Override
	public boolean updateStuImg(String id, String url) {
		try{
			studentDao.updateImgUrl(id, url);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public void updateStu(Student stu) {
		studentDao.update(stu);
	}
	
	@Override
	public Student getStu(String id) {
		return studentDao.get(id);
	}
	
}
