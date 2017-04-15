package com.zjicm.auth.dao;

import com.zjicm.auth.domain.User;
import com.zjicm.common.lang.sql.dao.BaseDAO;

public interface UserDao extends BaseDAO<User, Integer>{

    /**
     * 通过用户名和密码进行查找,返回用户
     *
     * @param account
     * @param password
     * @return
     */
    User getByNumPwd(String account, String password);


    /**
     * 通过学号／工号／账号获取用户信息
     *
     * @param number
     * @return
     */
    User getByNum(String number);
}
