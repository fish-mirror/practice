package com.zjicm.auth.dao;

import java.util.List;

public interface AuthDao {

	//得到院系用户的权限
	public List<Integer> getAuth(int facultyId);
}
