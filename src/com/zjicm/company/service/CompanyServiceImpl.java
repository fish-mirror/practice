package com.zjicm.company.service;

import java.util.List;

import com.zjicm.company.dao.CompanyDao;
import com.zjicm.dto.Page;
import com.zjicm.company.domain.Company;
import com.zjicm.auth.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zjicm.cooperation.dao.CooperateDao;

@Component
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private CooperateDao cooperateDao;

    @Override
    public void save(User u, Company com) {

    }

    @Override
    public void saveAndCoop(User u, Company com, String colId) {

    }

    @Override
    public List<Company> getCompanyList() {
        return null;
    }

    @Override
    public List<Company> getCompanyList(String colId) {
        return null;
    }

    @Override
    public Page pageForCompany(int pageSize, int page) {
        return null;
    }

    @Override
    public Page pageForCompany(String colId, int pageSize, int page) {
        return null;
    }

    @Override
    public boolean updateComImg(String id, String url) {
        return false;
    }

    @Override
    public void updateCompany(Company com) {

    }

    @Override
    public Company getCompany(String id) {
        return null;
    }

    @Override
    public void deleteCoop(Integer coopId) {

    }

    @Override
    public void deleteCoop(String colId, String comId) {

    }

    @Override
    public void addCoop(String colId, String comId) {

    }


//    @Override
//    public List<Company> getCompanyList() {
//
//        return companyDao.find();
//    }
//
//    @Override
//    public List<Company> getCompanyList(String colId) {
//
//        return companyDao.find(colId);
//    }
//
//    @Override
//    public Page pageForCompany(int pageSize, int page) {
//        int count = companyDao.count();
//        count = companyDao.count();
//        int totalPage = Page.countTotalPage(pageSize, count);
//        int offset = Page.countOffset(pageSize, page);
//        int length = pageSize;
//        int currentPage = Page.countCurrentPage(page);
//
//        List<Company> list = companyDao.find(offset, length);
//
//        Page p = new Page();
//        p.setPageSize(pageSize);
//        p.setCurrentPage(currentPage);
//        p.setAllRow(count);
//        p.setTotalPage(totalPage);
//        p.setList(list);
//        p.init();
//
//        return p;
//    }
//
//    @Override
//    public Page pageForCompany(String colId, int pageSize, int page) {
//        int count = companyDao.count(colId);
//        count = companyDao.count();
//        int totalPage = Page.countTotalPage(pageSize, count);
//        int offset = Page.countOffset(pageSize, page);
//        int length = pageSize;
//        int currentPage = Page.countCurrentPage(page);
//
//        List<Company> list = companyDao.find(offset, length);
//
//        Page p = new Page();
//        p.setPageSize(pageSize);
//        p.setCurrentPage(currentPage);
//        p.setAllRow(count);
//        p.setTotalPage(totalPage);
//        p.setList(list);
//        p.init();
//
//        return p;
//    }
//
//    @Override
//    public boolean updateComImg(String id, String url) {
//        try {
//            companyDao.updateImgUrl(id, url);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void updateCompany(Company Col) {
//        companyDao.update(Col);
//    }
//
//    @Override
//    public Company getCompany(String id) {
//        return companyDao.get(id);
//    }
//
//    @Override
//    public void save(User u, Company com) {
//        companyDao.save(u, com);
//    }
//
//    @Override
//    public void saveAndCoop(User u, Company com, String colId) {
//        this.save(u, com);
//        cooperateDao.save(new Cooperate(colId, com.getId()));
//    }
//
//    @Override
//    public void deleteCoop(Integer coopId) {
//        cooperateDao.delete(new Cooperate(coopId));
//    }
//
//    @Override
//    public void deleteCoop(String colId, String comId) {
//        cooperateDao.delete(colId, comId);
//    }
//
//    @Override
//    public void addCoop(String colId, String comId) {
//        cooperateDao.save(new Cooperate(colId, comId));
//
//    }
}
