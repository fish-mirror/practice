package com.zjicm.job.service;


import com.zjicm.common.lang.page.PageResult;
import com.zjicm.job.domain.Job;
import com.zjicm.job.dao.JobDao;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;


    @Override
    public Job get(Integer id) {
        if (id <= 0) return null;
        return jobDao.getById(id);
    }

    @Override
    public void save(Job job) {
        if (job == null) return;
        jobDao.save(job);
    }

    @Override
    public PageResult<Job> page(String userNumber, int status, int page, int size) {
        List<Criterion> criterionList = new ArrayList<>(2);
        if (StringUtils.isNotBlank(userNumber)) criterionList.add(Restrictions.eq("userNumber", userNumber));
        if (status > -1) criterionList.add(Restrictions.eq("status", status));

        return jobDao.getPageResult(criterionList, null, page, size);
    }
}
