package com.zjicm.student.dao;

import com.zjicm.common.dao.PracticeBaseDaoImpl;
import com.zjicm.student.domain.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl extends PracticeBaseDaoImpl<Student, Integer> implements StudentDao {

}
