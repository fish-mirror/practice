package com.zjicm.auth.service;

import com.zjicm.auth.dao.AuthorityDao;
import com.zjicm.auth.domain.Authority;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yujing on 2017/3/19.
 */
@Service
public class AuthorityServiceImpl implements AuthorityService{
    @Autowired
    AuthorityDao authorityDao;

    @Override
    public Set<Integer> getAuthorities(int userId) {
        List<Criterion> criteria = new ArrayList<>(1);
        criteria.add(Restrictions.eq("user_id",userId));
        List<Authority> authorities = authorityDao.getAll(criteria, null);
        if (CollectionUtils.isEmpty(authorities)) return null;

        Set<Integer> sets = new HashSet<>();
        authorities.forEach(authority -> sets.add(authority.getAuthority()));
        return sets;
    }
}
