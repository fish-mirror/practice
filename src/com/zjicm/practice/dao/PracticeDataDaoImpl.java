package com.zjicm.practice.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.zjicm.dto.LocationDTO;
import com.zjicm.entity.PracticeData;

@Component
public class PracticeDataDaoImpl implements PracticeDataDao {

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
	public void add(PracticeData pd) {
		hibernateTemplate.save(pd);
	}

	@Override
	public List<PracticeData> getList(int offset, int length) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from PracticeData order by id");
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}
	@Override
	public int count() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select count(*) from PracticeData");
		return Integer.parseInt(q.list().get(0).toString());
	}
	@Override
	public LocationDTO getLoactionData() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select count(*) from PracticeData");
		return null;
	}

}
