package com.zjicm.cooperation.dao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.zjicm.entity.Cooperate;
@Component
public class CooperateDaoImpl implements CooperateDao {

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
	public void save(Cooperate coop) {
		hibernateTemplate.save(coop);

	}

	@Override
	public Cooperate get(Integer id) {
		
		return hibernateTemplate.get(Cooperate.class,id);
	}
	@Override
	public Cooperate get(String institute,String comId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("Select * from Cooperate  where institute = ? and comId = ?");
		q.setString(0, institute);
		q.setString(1, comId);
		return (Cooperate) q.list().get(0);
	}

	@Override
	public void delete(Cooperate coop) {
	
		 hibernateTemplate.delete(coop);
	}
	
	@Override
	public void delete(String institute,String comId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("delete  Cooperate  where institute = ? and comId = ?");
		q.setString(0, institute);
		q.setString(1, comId);
		q.executeUpdate();
	}

}
