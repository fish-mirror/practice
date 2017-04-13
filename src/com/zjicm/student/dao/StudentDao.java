package com.zjicm.student.dao;

import com.zjicm.common.lang.sql.dao.BaseDAO;
import com.zjicm.student.beans.ClassInfoDto;
import com.zjicm.student.domain.Student;

import java.util.List;

public interface StudentDao extends BaseDAO<Student, Integer> {


    List<ClassInfoDto> getClassInfoByInstitute(int institute);
}
