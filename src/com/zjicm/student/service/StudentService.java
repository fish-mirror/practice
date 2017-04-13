package com.zjicm.student.service;

import java.util.List;

import com.zjicm.common.lang.page.PageResult;
import com.zjicm.student.beans.ClassInfoOut;
import com.zjicm.student.domain.Student;

public interface StudentService {
    /**
     * 保存
     *
     * @param student
     */
    void save(Student student);

    /**
     * 通过主键 ID 获取
     *
     * @param id
     * @return
     */
    Student get(Integer id);

    /**
     * 通过学号获取
     *
     * @param number
     * @return
     */
    Student getByNum(String number);


    /**
     * 获得该学院的班级列表
     *
     * @param institute
     * @return
     */
    List<ClassInfoOut> getClassList(int institute);

    PageResult<Student> pageStudent(int institute,
                                    boolean isGraduated,
                                    int grade,
                                    String major,
                                    int classIndex,
                                    int status,
                                    String name,
                                    int page,
                                    int size);


    //获得学院的状态分布
//    Map<String, StatusDTO> getStatus(String institute);


}
