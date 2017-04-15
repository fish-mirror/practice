package com.zjicm.auth.dao;

import com.zjicm.auth.beans.AuthDto;
import com.zjicm.auth.domain.Authority;
import com.zjicm.common.lang.sql.dao.BaseDAO;

import java.util.List;


public interface AuthorityDao extends BaseDAO<Authority, Integer> {

    /**
     * 获取一个用户的权限列表
     * @param userId
     * @return
     */
    List<Authority> getByUser(int userId);

    List<AuthDto> get(int userId);
}
