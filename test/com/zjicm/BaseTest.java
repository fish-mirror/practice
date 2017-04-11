package com.zjicm;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by yujing on 17-4-11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "/web")
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

}
