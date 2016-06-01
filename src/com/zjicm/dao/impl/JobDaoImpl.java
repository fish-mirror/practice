package com.zjicm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.zjicm.dao.JobDao;
import com.zjicm.entity.Job;

@Component
public class JobDaoImpl implements JobDao {

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
	public void save(Job j) {
		hibernateTemplate.save(j);
	}

	@Override
	public void update(Job j) {
		hibernateTemplate.update(j);
	}

	@Override
	public void updateStatus(Integer id, short status) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("update Job set status = ? where id = ?");
		q.setShort(0, status);
		q.setInteger(1, id);
		q.executeUpdate();
	}

	@Override
	public void addNum(Integer id,Integer haveNum) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("update Job set haveNum = ? where id = ?");
		q.setInteger(0, haveNum);
		q.setInteger(1, id);
		q.executeUpdate();
	}

	@Override
	public Job get(Integer id) {
		return hibernateTemplate.get(Job.class,id);
	}

	@Override
	public List<Job> list(int offset, int length) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from Job order by id");
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}

	@Override
	public List<Job> listByCom(String comId, int offset, int length) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from Job j where j.company.id = ? order by id");
		q.setString(0, comId);
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}

	@Override
	public List<Job> listStatus(int offset, int length) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from Job where status = 1 order by id");
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}
	
	@Override
	public List<Job> listByComStatus(String comId, int offset, int length) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from Job j where j.company.id = ? and status = 1 order by id");
		q.setString(0, comId);
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}
	@Override
	public int count() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select count(*) from Job");
		return Integer.parseInt(q.list().get(0).toString());
	}
	@Override
	public int countByCom(String comId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select count(*) from Job j where j.company.id = ?");
		q.setString(0, comId);
		return Integer.parseInt(q.list().get(0).toString());
	}
	@Override
	public int countStatus() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select count(*) from Job where status = 1");
		return Integer.parseInt(q.list().get(0).toString());
	}
	@Override
	public int countByComStatus(String comId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select count(*) from Job j where j.company.id = ? and status = 1");
		q.setString(0, comId);
		return Integer.parseInt(q.list().get(0).toString());
	}
	

}
