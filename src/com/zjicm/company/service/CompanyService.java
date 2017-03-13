package com.zjicm.company.service;


import java.util.List;

import com.zjicm.dto.Page;
import com.zjicm.company.domain.Company;
import com.zjicm.auth.domain.User;

public interface CompanyService {

    void save(User u, Company com);

    void saveAndCoop(User u, Company com, String colId);

    List<Company> getCompanyList();

    List<Company> getCompanyList(String colId);

    Page pageForCompany(int pageSize, int page);

    Page pageForCompany(String colId, int pageSize, int page);

    //更新企业用户的照片
    boolean updateComImg(String id, String url);

    //更新企业用户信息
    void updateCompany(Company com);

    //获得一个企业用户的信息
    Company getCompany(String id);

    void deleteCoop(Integer coopId);

    void deleteCoop(String colId, String comId);

    void addCoop(String colId, String comId);
}
