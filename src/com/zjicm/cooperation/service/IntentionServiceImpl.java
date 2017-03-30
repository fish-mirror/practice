package com.zjicm.cooperation.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zjicm.common.lang.page.PageResult;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zjicm.cooperation.dao.IntentionDao;
import com.zjicm.cooperation.domain.Intention;
import org.springframework.stereotype.Service;

@Service
public class IntentionServiceImpl implements IntentionService {

    @Autowired
    private IntentionDao intentionDao;

    @Override
    public void save(Intention intention) {
        if (intention == null) return;
        intentionDao.save(intention);
    }

    @Override
    public void delete(Intention intention) {
        if (intention == null) return;
        intentionDao.delete(intention);
    }

    @Override
    public Intention get(int id) {
        if (id <= 0) return null;
        return intentionDao.getById(id);
    }

    @Override
    public PageResult<Intention> page(int institute, int page, int size) {
        List<Criterion> criterionList = new ArrayList<>(1);
        if (institute > 0) criterionList.add(Restrictions.eq("institute", institute));

        return intentionDao.getPageResult(criterionList, null, page, size);
    }
}
