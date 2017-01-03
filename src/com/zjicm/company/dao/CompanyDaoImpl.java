package com.zjicm.company.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.zjicm.entity.Company;
import com.zjicm.entity.User;

@Component
public class CompanyDaoImpl implements CompanyDao {

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
	public void save(User u, Company com) {
		hibernateTemplate.save(u);
		hibernateTemplate.save(com);

	}

	@Override
	public Company get(String id) {
		
		return hibernateTemplate.get(Company.class,id);
	}

	@Override
	public void update(Company com) {
	
		 hibernateTemplate.update(com);
	}
	@Override
	public List<Company> find() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from Company order by id");
		return q.list();
	}
	@Override
	public List<Company> find(String institute) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("Select distinct new Company(c.id, c.name, c.type, c.location,c.address,c.tel,c.linkman) "
				+"from Company c,Cooperate coop where coop.institute  = ? and coop.comId = c.id");
		q.setString(0, institute);
		return q.list();
	}
	@Override
	public List<Company> find(int offset, int length) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from Company order by id");
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}
	@Override
	public List<Company> find(String institute, int offset, int length) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("Select new Company(c.id, c.name, c.type, c.location,c.address,c.tel,c.linkman) "
				+"from Company c,Cooperate coop where coop.institute  = ? and coop.comId = c.id");
		q.setString(0, institute);
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}
	
	
	@Override
	public int count() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select count(*) from Company");
		return Integer.parseInt(q.list().get(0).toString());
	}
	@Override
	public int count(String institute) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select count(*) from Cooperate where institute = ?");
		q.setString(0, institute);
		return Integer.parseInt(q.list().get(0).toString());
	}

	@Override
	public void updateImgUrl(String id, String url) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("update Company set imgUrl = ? where id = ?");
		q.setString(0, url);
		q.setString(1, id);
		q.executeUpdate();
	}
	

}
