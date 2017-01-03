package com.zjicm.auth.dao;

import com.zjicm.entity.User;

public interface UserDao {
	//新建一个用户信息
	public void save(User u);
	//通过用户名和密码进行查找,返回用户 
	public User search(User u);
	//更新用户密码
	public boolean updatePwd(String userId, String pwd);
	//通过id得到user信息
	public User getById(String userId);
}
