package com.zjicm.auth.service;


import com.zjicm.auth.dao.UserDao;
import com.zjicm.auth.domain.User;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;


    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User search(String account, String password) {
        List<Criterion> criterions = new ArrayList<>(2);
        criterions.add(Restrictions.eq("account", account));
        criterions.add(Restrictions.eq("password", password));
        return userDao.get(criterions, null);
    }

    @Override
    public User get(int id) {
        return userDao.getById(id);
    }

    @Override
    public User getByAccount(String account) {
        return userDao.getByField("account", account);
    }
}
