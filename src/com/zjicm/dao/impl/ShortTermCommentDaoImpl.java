package com.zjicm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zjicm.dao.ShortTermCommentDao;
import com.zjicm.entity.ShortTermComment;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShortTermCommentDaoImpl implements ShortTermCommentDao {

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
	public void add(ShortTermComment stc) {
		hibernateTemplate.save(stc);
	}

	@Override
	public void update(ShortTermComment stc) {
		hibernateTemplate.update(stc);
	}

	@Override
	public void delete(Integer id) {
		hibernateTemplate.delete(new ShortTermComment(id));
	}

	@Override
	public ShortTermComment get(Integer id) {
		return hibernateTemplate.get(ShortTermComment.class, id);
	}

	@Override
	public List<ShortTermComment> getList(Integer rid) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from ShortTermComment where rid = ? ");
		q.setInteger(0, rid);
		return q.list();
	}

	@Override
	public List<ShortTermComment> getList(String uid) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from ShortTermComment where user.id = ? ");
		q.setString (0, uid);
		return q.list();
	}

	@Override
	public float getAverageGrade(Integer rid) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select avg(grade) from ShortTermComment where rid = ? ");
		q.setInteger(0, rid);
		return Float.parseFloat(q.list().get(0).toString());
	}

}
