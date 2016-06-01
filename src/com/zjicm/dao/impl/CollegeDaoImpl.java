package com.zjicm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.zjicm.dao.CollegeDao;
import com.zjicm.entity.College;

@Component
public class CollegeDaoImpl implements CollegeDao {

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
	public void save(College col) {
		hibernateTemplate.save(col);

	}

	@Override
	public College get(String id) {
		
		return hibernateTemplate.get(College.class,id);
	}

	@Override
	public void update(College col) {
	
		 hibernateTemplate.update(col);
	}
	@Override
	public List<College> find(int offset, int length) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from College order by id");
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}
	
	
	@Override
	public int count() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select count(*) from College");
		return Integer.parseInt(q.list().get(0).toString());
	}

	@Override
	public void updateImgUrl(String id, String url) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("update College set imgUrl = ? where id = ?");
		q.setString(0, url);
		q.setString(1, id);
		q.executeUpdate();
	}

	

	
	

}
