package com.zjicm.cooperation.service;

import com.zjicm.common.lang.page.PageResult;
import com.zjicm.cooperation.dao.CooperationDao;
import com.zjicm.cooperation.domain.Cooperation;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CooperationServiceImpl implements CooperationService {

    @Autowired
    private CooperationDao cooperateDao;

    @Override
    public void save(Cooperation cooperate) {
        if (cooperate == null) return;
        cooperateDao.save(cooperate);
    }

    @Override
    public void delete(Cooperation cooperate) {
        if (cooperate == null) return;
        cooperateDao.delete(cooperate);
    }

    @Override
    public Cooperation get(int id) {
        if (id <= 0) return null;
        return cooperateDao.getById(id);
    }

    @Override
    public PageResult<Cooperation> page(int institute, int page, int size) {
        List<Criterion> criterionList = new ArrayList<>(1);
        if (institute > 0) criterionList.add(Restrictions.eq("institute", institute));
        
        return cooperateDao.getPageResult(criterionList, null, page, size);
    }
}
