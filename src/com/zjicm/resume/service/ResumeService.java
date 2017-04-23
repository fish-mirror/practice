package com.zjicm.resume.service;

import java.util.ArrayList;
import java.util.List;

import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.common.lang.util.StringUtil;
import com.zjicm.resume.beans.ResumeParams;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zjicm.resume.dao.ResumeDao;
import com.zjicm.resume.domain.Resume;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {

    @Autowired
    ResumeDao resumeDao;

    /**
     * 获取
     *
     * @param id
     * @return
     */
    public Resume get(int id) {
        return resumeDao.getById(id);
    }

    /**
     * 获取学生的简历列表
     *
     * @param number
     * @return
     */
    public PageResult<Resume> page(String number, int page, int size) {
        if (StringUtil.isBlank(number)) return null;

        List<Criterion> criteria = new ArrayList<>(1);
        criteria.add(Restrictions.eq("studentNumber", number));

        return resumeDao.getPageResult(criteria, null, page, size);
    }

    /**
     * 通过用户输入参数创建一条简历数据
     *
     * @param params
     * @param session
     * @return
     */
    public int createResume(ResumeParams params, UserSession session) {
        if (params == null || session == null) return 0;
        Resume resume = new Resume();
        resume.setCertificate(params.getCertificate());
        resume.setMajorClass(params.getMajor_class());
        resume.setPracticeExp(params.getPractice_exp());
        resume.setResumeUrl(params.getResume_url());
        resume.setSchoolExp(params.getSchool_exp());
        resume.setSelfComment(params.getSelf_comment());
        resume.setTitle(params.getTitle());
        resume.setWorksUrl(params.getWorks_url());

        resume.setStudentNumber(session.getNumber());
        resume.setCreator(session.getUserId());
        resume.setModifier(session.getUserId());

        resumeDao.save(resume);
        return resume.getId();
    }

    /**
     * 修改简历数据
     *
     * @param resume
     * @param params
     * @param session
     */
    public void modifyResume(Resume resume, ResumeParams params, UserSession session) {
        if (resume == null || params == null || session == null) return;

        if (StringUtil.isNotBlank(params.getCertificate())) resume.setCertificate(params.getCertificate());
        if (StringUtil.isNotBlank(params.getMajor_class())) resume.setMajorClass(params.getMajor_class());
        if (StringUtil.isNotBlank(params.getPractice_exp())) resume.setPracticeExp(params.getPractice_exp());
        if (StringUtil.isNotBlank(params.getResume_url())) resume.setResumeUrl(params.getResume_url());
        if (StringUtil.isNotBlank(params.getSchool_exp())) resume.setSchoolExp(params.getSchool_exp());
        if (StringUtil.isNotBlank(params.getSelf_comment())) resume.setSelfComment(params.getSelf_comment());
        if (StringUtil.isNotBlank(params.getTitle())) resume.setTitle(params.getTitle());
        if (StringUtil.isNotBlank(params.getWorks_url())) resume.setWorksUrl(params.getWorks_url());

        resume.setModifier(session.getUserId());
        resumeDao.save(resume);
    }

    public void delete(Resume resume) {
        if (resume == null) return;
        resumeDao.delete(resume);
    }

}
