package com.zjicm.auth.dao;

import com.zjicm.auth.beans.AuthDto;
import com.zjicm.auth.domain.Authority;
import com.zjicm.common.dao.PracticeBaseDaoImpl;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorityDaoImpl extends PracticeBaseDaoImpl<Authority, Integer> implements AuthorityDao {

    @Override
    public List<Authority> getByUser(int userId) {
        List<Criterion> criterions = new ArrayList<>(1);
        criterions.add(Restrictions.eq("userId", userId));
        return this.getAll(criterions, null);
    }

    @Override
    public List<AuthDto> get(int userId) {
        List<Criterion> criterions = new ArrayList<>(1);
        criterions.add(Restrictions.eq("userId", userId));
        ProjectionList projectionList = Projections.projectionList()
                                                   .add(Projections.property("authority")
                                                                   .as("authority"));
        return this.getAll(criterions, projectionList, AuthDto.class, null, null, null, null);
    }
}
