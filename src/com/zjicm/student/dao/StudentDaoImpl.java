package com.zjicm.student.dao;

import com.zjicm.common.dao.PracticeBaseDaoImpl;
import com.zjicm.student.beans.ClassInfoDto;
import com.zjicm.student.domain.Student;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDaoImpl extends PracticeBaseDaoImpl<Student, Integer> implements StudentDao {


    @Override
    public List<ClassInfoDto> getClassInfoByInstitute(int institute) {
        List<Criterion> criterions = new ArrayList<>(1);
        criterions.add(Restrictions.eq("institute", institute));
        List<String> properties = new ArrayList<>(3);
        properties.add("grade");
        properties.add("major");
        properties.add("classIndex");

        return distinct(criterions, properties, ClassInfoDto.class, null, null, null);
    }
}
