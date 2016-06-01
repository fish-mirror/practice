package com.zjicm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.zjicm.dao.UserDao;
import com.zjicm.entity.User;

@Component
public class UserDaoImpl implements UserDao{

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
	public void save(User u) {
		hibernateTemplate.save(u);
	}

	@Override
	public User search(User u) {
		session =  hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "from User where id = ? and pwd = ?";
		Query q = session.createQuery(hql);
		q.setString(0, u.getId());
		q.setString(1,u.getPwd());

		List<User> l = (List<User>)q.list();
		System.out.println("list返回的size："+l.size());
		if(l.size()!= 0){
			User user = l.get(0);
			//清除密码信息
			user.setPwd(null);
			return user;
		}else{
			return null;
		}
	}

	@Override
	public boolean updatePwd(String userId, String pwd) {
		session =  hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "update User set pwd = ? where id = ? ";
		Query q = session.createQuery(hql);
		q.setString(0, pwd);
		q.setString(1, userId);

		int resultNum = q.executeUpdate();
		
		if(resultNum!= 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public User getById(String userId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "from User where id = ? ";
		Query q = session.createQuery(hql);
		q.setString(0, userId);

		List l = q.list();
		if(l.size()!= 0){
			User user = (User)l.get(0);
			//清除密码信息
			user.setPwd(null);
			session.evict(user);
			return user;
		}else{
			return null;
		}
	}

}
