package com.zjicm.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.zjicm.dao.ShortTermReportDao;
import com.zjicm.dto.StudentShortTermReport;
import com.zjicm.entity.Company;
import com.zjicm.entity.ShortTermProject;
import com.zjicm.entity.ShortTermReport;

@Component
public class ShortTermReportDaoImpl implements ShortTermReportDao {

	HibernateTemplate hibernateTemplate;
	
	Session session;
	private HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Override
	public void add(String stuId, ShortTermProject shortTermProject) {
		ShortTermReport str = new ShortTermReport(stuId, shortTermProject);
		hibernateTemplate.save(str);
	}

	@Override
	public void update(Integer id, String url) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("update ShortTermReport set url = ? where id = ?");
		q.setString(0, url);
		q.setInteger(1,id);
		q.executeUpdate();
	}

	@Override
	public void delete(Integer id) {
		hibernateTemplate.delete(new ShortTermReport(id));
	}
	@Override
	public void delete(ShortTermReport str) {
		hibernateTemplate.delete(str);
	}

	@Override
	public ShortTermReport get(Integer id) {
		return hibernateTemplate.get(ShortTermReport.class, id);
	}

	@Override
	public List<StudentShortTermReport> getList(ShortTermProject shortTermProject) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql = "select distinct new com.zjicm.dto.StudentShortTermReport(s.id,s.name,s.institute,s.classname,stp.id,stp.name,str.id,str.url,str.date)"
				+"from Student s,ShortTermProject stp,ShortTermReport str " 
				+ "where s.id = str.stuId and str.shortTermProject.id = stp.id and stp.id = ? group by s order by s.id";
		Query q = session.createQuery(sql);
		q.setInteger(0, shortTermProject.getId());
		return q.list();
	}
	@Override
	public List<StudentShortTermReport> getListByInstitute(String institute){
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql = "select distinct new com.zjicm.dto.StudentShortTermReport(s.id,s.name,s.institute,s.classname,stp.id,stp.name,str.id,str.url,str.date)"
				+"from Student s,ShortTermProject stp,ShortTermReport str " 
				+ "where s.id = str.stuId and str.shortTermProject.id = stp.id and stp.institute = ? group by s order by s.id";
		Query q = session.createQuery(sql);
		q.setString(0, institute);
		return q.list();
	}

	@Override
	public List<StudentShortTermReport> getNoUrlListByInstitute(String institute) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql = "select distinct new com.zjicm.dto.StudentShortTermReport(s.id,s.name,s.institute,s.classname,stp.id,stp.name,str.id,str.url,str.date)"
				+"from Student s,ShortTermProject stp,ShortTermReport str " 
				+ "where s.id = str.stuId and str.shortTermProject.id = stp.id and str.url is null and stp.institute = ? group by s order by s.id";
		Query q = session.createQuery(sql);
		q.setString(0, institute);
		return q.list();
	}


	@Override
	public List<StudentShortTermReport> getListByCompany(String comId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql = "select distinct new com.zjicm.dto.StudentShortTermReport(s.id,s.name,s.institute,s.classname,stp.id,stp.name,str.id,str.url,str.date)"
				+"from Student s,ShortTermProject stp,ShortTermReport str " 
				+ "where s.id = str.stuId and str.shortTermProject.id = stp.id and stp.comId = ? group by s order by s.id";
		Query q = session.createQuery(sql);
		q.setString(0, comId);
		return q.list();
	}
	@Override
	public List<StudentShortTermReport> getNoUrlListByCompany(String comId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql = "select distinct new com.zjicm.dto.StudentShortTermReport(s.id,s.name,s.institute,s.classname,stp.id,stp.name,str.id,str.url,str.date)"
				+"from Student s,ShortTermProject stp,ShortTermReport str " 
				+ "where s.id = str.stuId and str.shortTermProject.id = stp.id and str.url is null and stp.comId = ? group by s order by s.id";
		Query q = session.createQuery(sql);
		q.setString(0, comId);
		return q.list();
	}
	@Override
	public ShortTermReport get(String stuId, String term) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql = "from ShortTermReport where stuId = ? and shortTermProject.term = ? ";
		Query q = session.createQuery(sql);
		q.setString(0, stuId);
		q.setString(1, term);
		List l = q.list();
		if(l.isEmpty()){
			return null;
		}else{
			return (ShortTermReport) l.get(0);
		}
		
	}
	@Override
	public ShortTermReport get(String stuId, Integer pid){
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql = "from ShortTermReport where stuId = ? and  pid = ? ";
		Query q = session.createQuery(sql);
		q.setString(0, stuId);
		q.setInteger(1, pid);
		List l = q.list();
		if(l.isEmpty()){
			return null;
		}else{
			return (ShortTermReport) l.get(0);
		}
	}
	@Override
	public List<ShortTermReport> getListByStu(String stuId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql = "from ShortTermReport where stuId = ?";
		Query q = session.createQuery(sql);
		q.setString(0, stuId);
		return q.list();
		
	}
	
	


}
