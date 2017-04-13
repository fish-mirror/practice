package com.zjicm.auth.dao;

import com.zjicm.DaoTest;
import com.zjicm.auth.beans.AuthDto;
import com.zjicm.auth.domain.Authority;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by yujing on 2017/4/12.
 */
public class AuthorityDaoTest extends DaoTest{
    @Autowired
    AuthorityDao authorityDao;
    @Test
    public void getByUser() throws Exception {
        List<Authority> authorities = authorityDao.getByUser(587);
        Assert.assertNotNull(authorities);
    }

    @Test
    public void get() throws Exception {
        List<AuthDto> authorities = authorityDao.get(587);
        Assert.assertNotNull(authorities);
    }

}