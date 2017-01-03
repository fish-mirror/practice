package com.zjicm.auth.service;

import javax.annotation.Resource;

import com.zjicm.auth.dao.UserDao;
import com.zjicm.entity.User;
import org.springframework.stereotype.Component;

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
