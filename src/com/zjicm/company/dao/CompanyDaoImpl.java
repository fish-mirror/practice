package com.zjicm.company.dao;

import com.zjicm.common.dao.PracticeBaseDaoImpl;

import com.zjicm.company.domain.Company;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoImpl extends PracticeBaseDaoImpl<Company, Integer> implements CompanyDao {

}
