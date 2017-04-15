package com.zjicm.company.service;

import com.zjicm.common.lang.page.PageResult;
import com.zjicm.company.dao.CompanyDao;
import com.zjicm.company.domain.Company;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Override
    public void save(Company company) {
        if (company == null) return;
        companyDao.save(company);
    }

    @Override
    public void delete(Company company) {
        if (company == null) return;
        companyDao.delete(company);
    }

    @Override
    public Company getByNum(String number) {
        if (StringUtils.isBlank(number)) return null;
        return companyDao.getByField("number", number);
    }

    @Override
    public Company get(Integer id) {
        if (id <= 0) return null;
        return companyDao.getById(id);
    }

    @Override
    public PageResult<Company> page(String institute, int page, int size) {
        List<Criterion> criterionList = new ArrayList<>(1);

        if (StringUtils.isNotBlank(institute)) criterionList.add(Restrictions.eq("institue", institute));

        return companyDao.getPageResult(criterionList, null, page, size);
    }
}
