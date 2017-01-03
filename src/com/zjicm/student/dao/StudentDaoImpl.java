package com.zjicm.student.dao;

import java.util.List;

import javax.annotation.Resource;

import com.zjicm.student.dao.StudentDao;
import com.zjicm.entity.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class StudentDaoImpl implements StudentDao {

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
	public void save(Student stu) {
		hibernateTemplate.save(stu);

	}

	@Override
	public Student get(String id) {
		
		return hibernateTemplate.get(Student.class,id);
	}

	@Override
	public void update(Student stu) {
	
		 hibernateTemplate.update(stu);
	}
	@Override
	public List<Student> find(int offset, int length) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from Student order by id");
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}
	@Override
	public List<Student> findByClassName(String classname, int offset,
			int length) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from Student where classname=? order by id");
		q.setString(0, classname);
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}
	@Override
	public List<Student> findGraduateClass(short graduate, int offset, int length) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from Student where graduate= ? order by id ");
		q.setShort(0, graduate);
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}
	
	
	
	
	@Override
	public int count() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select count(*) from Student");
		return Integer.parseInt(q.list().get(0).toString());
	}
	@Override
	public int countByClassName(String classname) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select count(*) from Student where classname = ?");
		q.setString(0, classname);
		return Integer.parseInt(q.list().get(0).toString());
	}
	@Override
	public int countGraduateClass(short graduate) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select count(*) from Student where graduate = ?");
		q.setShort(0, graduate);
		return Integer.parseInt(q.list().get(0).toString());
	}
	@Override
	public List<String> getClassList(String institute) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select distinct classname from Student where institute = ?");
		q.setString(0, institute);
		return q.list();
	}
	@Override
	public List<String> getMajorList(String institute) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select distinct major from Student where institute = ?");
		q.setString(0, institute);
		return q.list();
	}
	@Override
	public List countGraduateStatus(short graduate) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select status,count(id) from Student where graduate = ? group by status");
		q.setShort(0, graduate);
		return q.list();
	}
	@Override
	public List countClassStatus(String classname) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select status,count(id) from Student where classname = ? group by status");
		q.setString(0,classname);
		return q.list();
	}
	@Override
	public List countInstituteStatus(String institute) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("select status,count(id) from Student where institute = ? group by status");
		q.setString(0,institute);
		return q.list();
	}
	@Override
	public List<Student> findInGraduateStatus(short graduate, short status) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Student> findInClassStatus(String classname, short status) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Student> findInInstituteStatus(String institute, short status) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void updateImgUrl(String id, String url) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query q = session.createQuery("update Student set imgUrl = ? where id = ?");
		q.setString(0, url);
		q.setString(1, id);
		q.executeUpdate();
	}

	

	
	

}
