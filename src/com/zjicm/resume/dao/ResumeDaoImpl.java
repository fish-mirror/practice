package com.zjicm.resume.dao;

import com.zjicm.common.dao.PracticeBaseDaoImpl;
import com.zjicm.resume.domain.Resume;
import org.springframework.stereotype.Repository;

@Repository
public class ResumeDaoImpl extends PracticeBaseDaoImpl<Resume, Integer> implements ResumeDao {

}