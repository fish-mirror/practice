package com.zjicm.teacher.service;

import java.util.List;

import com.zjicm.teacher.dao.TeacherDao;
import com.zjicm.dto.Page;
import com.zjicm.teacher.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;


    @Override
    public void save(Teacher teacher) {
        teacherDao.save(teacher);
    }

    @Override
    public Teacher get(Integer id) {
        return teacherDao.getById(id);
    }

    @Override
    public Teacher getByNum(String number) {
        return teacherDao.getByField("number", number);
    }

    @Override
    public void updateImgUrl(String id, String url) {

    }

    @Override
    public List<Teacher> find(int offset, int length) {
        return null;
    }


    @Override
    public Page pageForCollege(int pageSize, int page) {
        return null;
    }

    @Override
    public boolean updateColImg(String id, String url) {
        return false;
    }

    @Override
    public void updateCol(Teacher col) {

    }

    @Override
    public Teacher getCol(String id) {
        return null;
    }
}
