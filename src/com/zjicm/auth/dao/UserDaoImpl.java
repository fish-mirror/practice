package com.zjicm.auth.dao;


import com.zjicm.auth.domain.User;
import com.zjicm.common.dao.PracticeBaseDaoImpl;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl extends PracticeBaseDaoImpl<User, Integer> implements UserDao {


    @Override
    public User getByNumPwd(String account, String password) {
        List<Criterion> criterions = new ArrayList<>(2);
        criterions.add(Restrictions.eq("number", account));
        criterions.add(Restrictions.eq("password", password));
        return this.get(criterions, null);
    }


    @Override
    public User getByNum(String number) {
        return this.getByField("number", number);
    }
}
