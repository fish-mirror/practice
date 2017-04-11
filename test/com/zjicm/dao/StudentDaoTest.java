package com.zjicm.dao;

import com.zjicm.DaoTest;
import com.zjicm.student.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentDaoTest extends DaoTest{

    @Autowired
    private StudentDao studentDao;

    @Override
    public void saveTest() {
        super.saveTest();
    }

    @Override
    public void getTest() {
        super.getTest();
    }

    @Override
    public void listTest() {
        super.listTest();
    }

    @Override
    public void deleteTest() {
        super.deleteTest();
    }
}
