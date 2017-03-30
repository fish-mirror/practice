package com.zjicm.company.service;

import com.zjicm.common.lang.page.PageResult;
import com.zjicm.company.domain.Company;

public interface CompanyService {

    /**
     * 添加／更新
     *
     * @param company
     */
    void save(Company company);

    /**
     * 删除
     *
     * @param company
     */
    void delete(Company company);

    /**
     * 通过编号获取企业信息
     *
     * @param number
     * @return
     */
    Company getByNumber(String number);

    /**
     * 获取企业信息
     *
     * @param id
     * @return
     */
    Company get(Integer id);

    /**
     * 分页获取企业列表
     *
     * @param institute 按学院
     * @param page
     * @param size
     * @return
     */
    PageResult<Company> page(String institute, int page, int size);

}
