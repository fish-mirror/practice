package com.zjicm.student.dao;

import com.zjicm.common.lang.page.PageResult;
import com.zjicm.common.lang.sql.dao.BaseDAO;
import com.zjicm.student.beans.ClassInfoDto;
import com.zjicm.student.domain.Student;

import java.util.List;

public interface StudentDao extends BaseDAO<Student, Integer> {

    /**
     * 获取班级列表
     *
     * @param institute
     * @return
     */
    List<ClassInfoDto> getClassInfoByInstitute(int institute);

    /**
     * 查看学生的分页信息
     *
     * @param institute  学院
     * @param grade      年级
     * @param major      专业代号
     * @param classIndex 班级号
     * @param status     实习状态
     * @param page
     * @param size
     * @return
     */
    PageResult<Student> page(int institute,
                             int grade,
                             String major,
                             int classIndex,
                             int status,
                             String name,
                             int page,
                             int size);

}
