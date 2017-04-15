package com.zjicm.job.dao;

import com.zjicm.common.dao.PracticeBaseDaoImpl;
import com.zjicm.job.domain.Job;
import org.springframework.stereotype.Repository;

@Repository
public class JobDaoImpl extends PracticeBaseDaoImpl<Job, Integer> implements JobDao {

}
