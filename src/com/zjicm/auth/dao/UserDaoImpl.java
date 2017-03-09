package com.zjicm.auth.dao;


import com.zjicm.auth.domain.User;
import com.zjicm.common.dao.PracticeBaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends PracticeBaseDaoImpl<User, Integer> implements UserDao {


}
