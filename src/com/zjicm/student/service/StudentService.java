package com.zjicm.student.service;

import java.util.List;

import com.zjicm.student.domain.Student;

public interface StudentService {

    void save(Student stu);

    Student get(Integer id);

    Student getByNum(String number);

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
//    Page pageForStudentInfo(Short graduate, String classname, String num, int pageSize, int page);

    /**
     * 获得该学院的班级列表
     *
     * @param institute
     * @return
     */
    List<String> getClassList(String institute);

    List<String> getMajorList(String institute);

    //获得学院的状态分布
//    Map<String, StatusDTO> getStatus(String institute);

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
