package com.zjicm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.zjicm.dao.IntentionDao;
import com.zjicm.entity.Intention;

@Component
public class IntentionDaoImpl implements IntentionDao {

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
	public void save(Intention i) {
		hibernateTemplate.save(i);

	}

	@Override
	public void update(Intention i) {
		hibernateTemplate.update(i);
	}

	@Override
	public Intention get(int id) {
		return hibernateTemplate.get(Intention.class,id);
	}

	@Override
	public List<Intention> getList(String institute) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from Intention where institute = ? order by id");
		q.setString(0, institute);
		return q.list();
	}
	@Override
	public void delete(int id) {
		hibernateTemplate.delete(new Intention(id));
		
	}
	

}
