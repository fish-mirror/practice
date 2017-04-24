package com.zjicm.cooperation.service;

import com.zjicm.auth.dao.UserDao;
import com.zjicm.auth.domain.User;
import com.zjicm.auth.enums.Role;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.company.dao.CompanyDao;
import com.zjicm.company.domain.Company;
import com.zjicm.cooperation.beans.CompanyRegisterParams;
import com.zjicm.cooperation.dao.CooperationDao;
import com.zjicm.cooperation.domain.Cooperation;
import com.zjicm.cooperation.enums.CooperationStatus;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 校企合作业务逻辑
 */
@Service
public class CooperationService {

    @Autowired
    private CooperationDao cooperateDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CompanyDao companyDao;

    public void save(Cooperation cooperate) {
        if (cooperate == null) return;
        cooperateDao.save(cooperate);
    }

    /**
     * 删除
     *
     * @param cooperate
     */
    public void delete(Cooperation cooperate) {
        if (cooperate == null) return;
        cooperateDao.delete(cooperate);
    }

    /**
     * 获取
     *
     * @param id
     * @return
     */
    public Cooperation get(int id) {
        if (id <= 0) return null;
        return cooperateDao.getById(id);
    }

    /**
     * 分页获取合作企业列表
     *
     * @param institute
     * @param status
     * @param page
     * @param size
     * @return
     */
    public PageResult<Cooperation> page(int institute, CooperationStatus status, int page, int size) {
        List<Criterion> criterionList = new ArrayList<>(2);
        if (status != null) criterionList.add(Restrictions.eq("status", status.getValue()));
        if (institute > 0) criterionList.add(Restrictions.eq("institute", institute));

        return cooperateDao.getPageResult(criterionList, null, page, size);
    }

    /**
     * 创建企业用户
     *
     * @param params
     * @return
     */
    public Company createCampay(CompanyRegisterParams params) {
        if (params == null) return null;
        if (userDao.getByField("number", params.getNumber()) != null) return null;

        User user = new User();
        user.setNumber(params.getNumber());
        user.setPassword(params.getPassword());
        user.setRoleId(Role.company.getValue());

        Company company = new Company();
        company.setNumber(params.getNumber());
        company.setName(params.getName());

        userDao.save(user);
        companyDao.save(company);

        return company;
    }

    /**
     * 创建合作关系
     *
     * @param insititute
     * @param company
     * @param session
     */
    public int createCooperation(int insititute, Company company, UserSession session) {
        if (insititute < 0 || company == null || session == null) return 0;
        Cooperation cooperation = new Cooperation();
        cooperation.setInstitute(insititute);
        cooperation.setCompany(company);
        if (session.getRoleId() == Role.teacher.getValue()) cooperation.setStatus(1);
        cooperation.setCreator(session.getUserId());
        cooperateDao.save(cooperation);
        return cooperation.getId();
    }

}
