package com.zjicm.student.service;

import java.util.List;
import java.util.Map;

import com.zjicm.dto.Page;
import com.zjicm.dto.StatusDTO;
import com.zjicm.student.domain.Student;

public interface IStudentService {


    /**
     * 查看学生的分页信息
     *
     * @param graduate
     * @param classname
     * @param num
     * @param pageSize
     * @param page
     * @return
     */
    Page pageForStudentInfo(Short graduate, String classname, String num, int pageSize, int page);

    /**
     * 获得该学院的班级列表
     *
     * @param institute
     * @return
     */
    List<String> getClassList(String institute);

    List<String> getMajorList(String institute);

    //获得学院的状态分布
    Map<String, StatusDTO> getStatus(String institute);

    //更新学生个人信息
    boolean updateStuImg(String id, String url);

    //更新学生信息
    void updateStu(Student stu);

    //获得一个学生信息
    Student getStu(String id);

    void save(Student stu);

    Student get(String id);

    //更新学生信息
    void update(Student stu);

    void updateImgUrl(String id, String url);

    List<Student> find(int offset, int length);

    List<Student> findByClassName(String classname, int offset, int length);

    List<Student> findGraduateClass(short graduate, int offset, int length);

    int count();

    int countByClassName(String classname);

    int countGraduateClass(short graduate);

    //状态数量的查询

    /**
     * 按是否毕业班来查询各个状态下的人数
     *
     * @param graduate
     * @return
     */
    List countGraduateStatus(short graduate);

    /**
     * 按班级名查询各个实习状态下的人数
     *
     * @param classname
     * @return
     */
    List countClassStatus(String classname);

    /**
     * 按学院查询各个实习状态下的人数
     *
     * @param institute
     * @return
     */
    List countInstituteStatus(String institute);

    //具体状态下的学生信息查询
    List<Student> findInGraduateStatus(short graduate, short status);

    List<Student> findInClassStatus(String classname, short status);

    List<Student> findInInstituteStatus(String institute, short status);

}