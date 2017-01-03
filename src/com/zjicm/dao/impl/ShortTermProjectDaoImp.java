package com.zjicm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zjicm.dao.ShortTermProjectDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.zjicm.entity.Company;
import com.zjicm.entity.ShortTermProject;

@Component
public class ShortTermProjectDaoImp implements ShortTermProjectDao {

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
	public Integer add(ShortTermProject stp) {
		return (Integer)hibernateTemplate.save(stp);
	}
	@Override
	public void update(ShortTermProject stp) {
		hibernateTemplate.update(stp);
	}
	@Override
	public void updateStatus(Integer id, short status) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("update ShortTermProject set status = ? where id = ?");
		q.setShort(0,status);
		q.setInteger(1, id);
		q.executeUpdate();
	}
	@Override
	public void updateUrl(Integer id, String url) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("update ShortTermProject set fileUrl = ? where id = ?");
		q.setString(0, url);
		q.setInteger(1, id);
		q.executeUpdate();
	}
	@Override
	public ShortTermProject get(Integer id) {
		return hibernateTemplate.get(ShortTermProject.class,id);
	}
	@Override
	public List<ShortTermProject> getList(Short status, String institute, String comId, String term) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql = "from ShortTermProject where 1 = 1 ";
		if(status != null){
			sql += "and status = ? ";
		}
		if(institute != null){
			sql += "and institute = ? ";
		}
		if(comId != null){
			sql += "and comId = ? ";
		}
		if(term != null){
			sql += "and term = ? ";
		}
		Query q = session.createQuery(sql + "order by id");
		int i = 0;
		if(status != null){
			q.setShort(i,status);
			i++;
		}
		if(institute != null){
			q.setString(i,institute);
			i++;
		}
		if(comId != null){
			q.setEntity(i, new Company(comId));
			i++;
		}
		if(term != null){
			q.setString(i,term);
			i++;
		}
		return q.list();
	}
	@Override
	public ShortTermProject getId(String name) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql = "from ShortTermProject where name = ? ";
		Query q = session.createQuery(sql);
		q.setString(0, name);
		if(q.list().size()>0){
			return (ShortTermProject) q.list().get(0);
		}else{
			return null;
		}
		
	}
	
	
	

}
