package com.zjicm.resume.service;

import java.util.List;

import com.zjicm.resume.domain.Resume;


public interface ResumeService {

    /**
     * 保存或更新
     * @param resume
     */
    void save(Resume resume);

    /**
     * 获取
     *
     * @param id
     * @return
     */
    Resume get(int id);

    /**
     * 获取学生的简历列表
     * @param number
     * @return
     */
    List<Resume> list(String number);

    /**
     * 删除
     *
     * @param resume
     */
    void delete(Resume resume);

}
