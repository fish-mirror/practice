package com.zjicm.teacher.service;

import java.util.List;

import com.zjicm.teacher.dao.TeacherDao;
import com.zjicm.dto.Page;
import com.zjicm.teacher.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    TeacherDao teacherDao;


    @Override
    public void save(Teacher col) {

    }

    @Override
    public Teacher get(String id) {
        return null;
    }

    @Override
    public void update(Teacher col) {

    }

    @Override
    public void updateImgUrl(String id, String url) {

    }

    @Override
    public List<Teacher> find(int offset, int length) {
        return null;
    }

    @Override
    public int count() {
        return 0;
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
