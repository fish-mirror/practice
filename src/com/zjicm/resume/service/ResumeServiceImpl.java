package com.zjicm.resume.service;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zjicm.resume.dao.ResumeDao;
import com.zjicm.resume.domain.Resume;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    ResumeDao resumeDao;

    @Override
    public void save(Resume resume) {
        resumeDao.save(resume);
    }

    @Override
    public Resume get(int id) {
        return resumeDao.getById(id);
    }

    @Override
    public List<Resume> list(String number) {
        List<Criterion> criteria = new ArrayList<>(1);
        criteria.add(Restrictions.eq("stu_id", number));

        return resumeDao.getAll(criteria, null);
    }

    @Override
    public void delete(Resume resume) {
        resumeDao.delete(resume);
    }

}
