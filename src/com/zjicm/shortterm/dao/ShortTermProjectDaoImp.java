package com.zjicm.shortterm.dao;


import com.zjicm.common.dao.PracticeBaseDaoImpl;
import com.zjicm.shortterm.domain.ShortTermProject;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShortTermProjectDaoImp extends PracticeBaseDaoImpl<ShortTermProject, Integer> implements ShortTermProjectDao {

    @Override
    public List<ShortTermProject> getAllCanSelected(int institute, String term) {
        if (institute <= 0 || StringUtils.isBlank(term)) return null;

        List<Criterion> criteria = new ArrayList<>();
        criteria.add(Restrictions.eq("institute", institute));
        criteria.add(Restrictions.eq("term", term));

        List<Order> orders = new ArrayList<>();
        orders.add(Order.desc("id"));
        return getAll(criteria, orders);
    }
}
