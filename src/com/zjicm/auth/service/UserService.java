package com.zjicm.auth.service;

import com.zjicm.auth.domain.User;

public interface UserService {

    /**
     * 新建一个用户信息
     *
     * @param user
     */
    void save(User user);

    /**
     * 通过用户名和密码进行查找,返回用户
     *
     * @param account
     * @param password
     * @return
     */
    User search(String account, String password);

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    User get(int id);

    /**
     * 通过学号／工号／账号获取用户信息
     *
     * @param number
     * @return
     */
    User getByNum(String number);


}
