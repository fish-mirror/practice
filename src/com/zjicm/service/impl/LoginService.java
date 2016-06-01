package com.zjicm.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.zjicm.dao.UserDao;
import com.zjicm.dao.impl.UserDaoImpl;
import com.zjicm.entity.User;
import com.zjicm.service.ILoginService;
import com.zjicm.util.SessionUtil;

@Component
public class LoginService implements ILoginService {

	UserDao udao;
	
	public UserDao getUdao() {
		return udao;
	}
	@Resource
	public void setUdao(UserDao udao) {
		this.udao = udao;
	}
	
	
	@Override
	public User login(User u) {
		return udao.search(u);
	}
	@Override
	public String addSession(User u) {
		
		return null;	
	}

	

}
