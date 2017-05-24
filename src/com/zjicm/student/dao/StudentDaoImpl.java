package com.zjicm.student.dao;

import com.zjicm.common.dao.PracticeBaseDaoImpl;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.student.beans.ClassInfoDto;
import com.zjicm.student.beans.StatusDto;
import com.zjicm.student.domain.Student;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.*;
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

    @Override
    public PageResult<Student> page(int institute,
                                    int grade,
                                    String major,
                                    int classIndex,
                                    int status,
                                    String name,
                                    int page,
                                    int size) {
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("number"));

        List<Criterion> criterions = new ArrayList<>();
        if (StringUtils.isNotBlank(name)) {
            criterions.add(Restrictions.like("name", "%" + name + "%"));
        }

        if (institute > 0) criterions.add(Restrictions.eq("institute", institute));
        if (grade > 12) criterions.add(Restrictions.eq("grade", grade));
        else criterions.add(Restrictions.gt("grade", 12));
        if (StringUtils.isNotBlank(major)) {
            criterions.add(Restrictions.eq("major", major));
            if (classIndex > 0) criterions.add(Restrictions.eq("classIndex", classIndex));
        }
        if (status > -1) criterions.add(Restrictions.eq("status", status));


        return getPageResult(criterions, null, page, size);
    }

    @Override
    public List<StatusDto> getStatusDistribute(int institute, int grade, String major, int classIndex) {
        List<Criterion> criterions = new ArrayList<>();

        if (institute > 0) criterions.add(Restrictions.eq("institute", institute));
        if (grade > 10) criterions.add(Restrictions.eq("grade", grade));
        if (StringUtils.isNotBlank(major)) {
            criterions.add(Restrictions.eq("major", major));
            if (classIndex > 0) criterions.add(Restrictions.eq("classIndex", classIndex));
        }

        ProjectionList projectionList = Projections.projectionList()
                                                   .add(Projections.groupProperty("status")
                                                                   .as("status"))
                                                   .add(Projections.count("id").as("total"));

        return getAll(criterions, projectionList, StatusDto.class, null, null, null, null);
    }
}
