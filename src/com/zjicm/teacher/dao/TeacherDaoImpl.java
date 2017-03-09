package com.zjicm.teacher.dao;

import com.zjicm.common.dao.PracticeBaseDaoImpl;
import com.zjicm.teacher.domain.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDaoImpl extends PracticeBaseDaoImpl<Teacher, Integer> implements TeacherDao {

}
