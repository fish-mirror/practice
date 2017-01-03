package com.zjicm.student.service;

import java.util.List;

import com.zjicm.shortterm.dao.ShortTermCommentDao;
import com.zjicm.shortterm.dao.ShortTermProjectDao;
import com.zjicm.shortterm.dao.ShortTermReportDao;
import com.zjicm.entity.Resume;
import com.zjicm.entity.ShortTermComment;
import com.zjicm.entity.ShortTermProject;
import com.zjicm.entity.ShortTermReport;
import com.zjicm.shortterm.service.IShortTermService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

public interface IResumeService {
	
	public void add(Resume r);
	public void update(Resume r);
	public Resume getResume(int id);
	public List<Resume> getResumeList(String stuId);
	public void delete(int id);

}
