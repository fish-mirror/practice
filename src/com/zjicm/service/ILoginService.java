package com.zjicm.service;

import javax.servlet.http.HttpSession;

import com.zjicm.entity.User;

public interface ILoginService {

	public User login(User u);
	public String addSession(User u);
}
