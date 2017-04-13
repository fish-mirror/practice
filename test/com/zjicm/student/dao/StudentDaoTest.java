package com.zjicm.student.dao;

import com.zjicm.DaoTest;
import com.zjicm.student.beans.ClassInfoDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yujing on 2017/4/13.
 */
public class StudentDaoTest extends DaoTest{
    @Autowired
    private StudentDao studentDao;
    @Test
    public void getClassInfoByInstitute(){
        List<ClassInfoDto> list = studentDao.getClassInfoByInstitute(11);
        Assert.assertNotNull(list);
    }

}