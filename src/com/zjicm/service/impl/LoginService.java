package com.zjicm.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.zjicm.entity.User;
import org.springframework.stereotype.Component;

import com.zjicm.dao.UserDao;
import com.zjicm.service.ILoginService;

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
