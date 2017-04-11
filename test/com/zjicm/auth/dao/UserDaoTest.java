package com.zjicm.auth.dao;

import com.zjicm.DaoTest;
import com.zjicm.auth.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by yujing on 2017/4/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "/web")
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserDaoTest extends DaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void getByAccountPwd() throws Exception {
        User user = userDao.getByNumPwd("130806228", "130806228");
        Assert.assertNotNull(user);
    }

    @Test
    public void getByNum() throws Exception {
        User user = userDao.getByNum("130806228");
        Assert.assertNotNull(user);
    }

    @Test
    public void saveTest() {
        User user = new User();
        user.setNumber("test");
        user.setPassword("test");
        userDao.save(user);
        Assert.assertNotEquals(java.util.Optional.ofNullable(user.getId()), 0);
    }

    @Test
    public void getByIdTest() {
        Assert.assertNotNull(userDao.getById(1));
    }


}