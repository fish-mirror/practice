package com.zjicm.practice.service;

import com.zjicm.common.lang.page.PageResult;
import com.zjicm.practice.dao.PracticeCommentDao;
import com.zjicm.practice.dao.PracticeInfoDao;
import com.zjicm.practice.domain.PracticeComment;
import com.zjicm.practice.domain.PracticeInfo;
import com.zjicm.practice.enums.PracticeStatus;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 实习信息查询支持
 * <p>
 * Created by yujing on 2017/5/20.
 */
@Service
public class PracticeInfoService {
    @Autowired
    private PracticeInfoDao practiceInfoDao;
    @Autowired
    private PracticeCommentDao practiceCommentDao;

    /**
     * 获取一条实习信息
     *
     * @param id
     * @return
     */
    public PracticeInfo getPracticeInfo(int id) {
        return practiceInfoDao.getById(id);
    }

    /**
     * 获取一条实习信息
     *
     * @param id
     * @param number
     * @return
     */
    public PracticeInfo getPracticeInfo(int id, String number) {
        if (id <= 0 || StringUtils.isBlank(number)) return null;

        List<Criterion> criterionList = new ArrayList<>(2);
        criterionList.add(Restrictions.eq("id", id));
        criterionList.add(Restrictions.eq("student.number", number));

        return practiceInfoDao.get(criterionList, null);
    }

    /**
     * 获取一条实习信息
     *
     * @param id
     * @param institute
     * @return
     */
    public PracticeInfo getPracticeInfo(int id, int institute) {
        if (id <= 0 || institute <= 0) return null;

        List<Criterion> criterionList = new ArrayList<>(2);
        criterionList.add(Restrictions.eq("id", id));
        criterionList.add(Restrictions.eq("institute", institute));

        return practiceInfoDao.get(criterionList, null);
    }

    /**
     * 获取一条实习信息
     *
     * @param id
     * @param number
     * @return
     */
    public PracticeInfo getPracticeInfoByCompany(int id, String number) {
        if (id <= 0 || StringUtils.isBlank(number)) return null;

        List<Criterion> criterionList = new ArrayList<>(2);
        criterionList.add(Restrictions.eq("id", id));
        criterionList.add(Restrictions.eq("companyNumber", number));

        return practiceInfoDao.get(criterionList, null);
    }

    /**
     * 获取实习信息的分页数据
     *
     * @param number
     * @param page
     * @param size
     * @return
     */
    public PageResult<PracticeInfo> pagePracticeInfoByStudent(String number, int page, int size) {
        List<Criterion> criterionList = new ArrayList<>(1);
        criterionList.add(Restrictions.eq("student.number", number));

        List<Order> orders = new ArrayList<>();
        orders.add(Order.desc("id"));

        return practiceInfoDao.getPageResult(criterionList, orders, page, size);
    }

    /**
     * 根据学院获取实习信息的分页数据
     *
     * @param institute
     * @param status
     * @param page
     * @param size
     * @return
     */
    public PageResult<PracticeInfo> pagePracticeInfoByInstitute(int institute,
                                                                PracticeStatus status,
                                                                int page,
                                                                int size) {
        List<Criterion> criterionList = new ArrayList<>(2);
        criterionList.add(Restrictions.eq("institute", institute));
        if (status != null) criterionList.add(Restrictions.eq("status", status.getValue()));

        List<Order> orders = new ArrayList<>();
        orders.add(Order.desc("id"));

        return practiceInfoDao.getPageResult(criterionList, orders, page, size);
    }

    public PageResult<PracticeInfo> pagePracticeInfoByCompany(String  number,
                                                              PracticeStatus status,
                                                              int page,
                                                              int size) {
        List<Criterion> criterionList = new ArrayList<>(2);
        criterionList.add(Restrictions.eq("companyNumber", number));
        if (status != null) criterionList.add(Restrictions.eq("status", status.getValue()));

        List<Order> orders = new ArrayList<>();
        orders.add(Order.desc("id"));

        return practiceInfoDao.getPageResult(criterionList, orders, page, size);
    }

    /**
     * 获取实习评分数据
     *
     * @param practiceId
     * @param userId
     * @return
     */
    public PracticeComment getPracticeComment(int practiceId, int userId) {
        if (practiceId <= 0 || userId <= 0) return null;

        List<Criterion> criterionList = new ArrayList<>(2);
        criterionList.add(Restrictions.eq("practiceId", practiceId));
        criterionList.add(Restrictions.eq("teacherUserId", userId));

        return practiceCommentDao.get(criterionList, null);
    }


}
