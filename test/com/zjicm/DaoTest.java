package com.zjicm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by yujing on 2017/4/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "/web")
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Test
    public void saveTest(){

    }

    @Test
    public void getByIdTest(){

    }
    @Test
    public void listTest(){

    }

    @Test
    public void deleteTest(){

    }
}
