package com.zjicm.cooperation.service;

import java.util.ArrayList;
import java.util.List;


import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.common.lang.util.StringUtil;
import com.zjicm.cooperation.beans.IntentionParams;
import com.zjicm.cooperation.beans.IntentionPatchParams;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zjicm.cooperation.dao.IntentionDao;
import com.zjicm.cooperation.domain.Intention;
import org.springframework.stereotype.Service;

/**
 * 合作意向业务逻辑类
 */
@Service
public class IntentionService {

    @Autowired
    private IntentionDao intentionDao;


    public Intention get(int id) {
        if (id <= 0) return null;
        return intentionDao.getById(id);
    }

    public PageResult<Intention> page(int institute, int page, int size) {
        List<Criterion> criterionList = new ArrayList<>(1);
        if (institute > 0) criterionList.add(Restrictions.eq("institute", institute));

        return intentionDao.getPageResult(criterionList, null, page, size);
    }

    /**
     * 创建合作意向
     *
     * @param params
     * @param session
     * @return
     */
    public int createIntention(IntentionParams params, UserSession session) {
        if (params == null || session == null) return 0;
        Intention intention = new Intention();
        intention.setTitle(params.getTitle());
        intention.setContent(params.getContent());

        intention.setCreator(session.getUserId());
        intention.setModifier(session.getUserId());
        intention.setInstitute(session.getInstitute());

        intentionDao.save(intention);
        return intention.getId();
    }

    /**
     * 修改合作意向
     *
     * @param intention
     * @param params
     * @param session
     * @return
     */
    public int modifyIntention(Intention intention, IntentionPatchParams params, UserSession session) {
        if (intention == null || params == null || session == null) return 0;
        if (StringUtil.isNotBlank(params.getTitle())) intention.setTitle(params.getTitle());
        if (StringUtil.isNotBlank(params.getContent())) intention.setContent(params.getContent());

        intention.setModifier(session.getUserId());
        intention.setInstitute(session.getInstitute());

        intentionDao.save(intention);
        return intention.getId();
    }

    /**
     * 删除
     *
     * @param intention
     */
    public void delete(Intention intention) {
        if (intention == null) return;
        intentionDao.delete(intention);
    }

}
