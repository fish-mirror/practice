package com.zjicm.resume;

import java.util.List;

import javax.annotation.Resource;

import com.zjicm.entity.Resume;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class ResumeDaoImpl implements ResumeDao {

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
	public void save(Resume r) {
		hibernateTemplate.save(r);

	}

	@Override
	public void update(Resume r) {
		hibernateTemplate.update(r);
	}

	@Override
	public Resume get(int id) {
		return hibernateTemplate.get(Resume.class,id);
	}

	@Override
	public List<Resume> getList(String stuId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from Resume where stuId = ? order by id");
		q.setString(0, stuId);
		return q.list();
	}
	@Override
	public void delete(int id) {
		hibernateTemplate.delete(new Resume(id));
		
	}
	

}
