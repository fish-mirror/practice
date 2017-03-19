package com.zjicm.auth.service;

import java.util.List;
import java.util.Set;

/**
 * 权限相关业务的数据方法
 *
 * Created by yujing on 2017/3/19.
 */
public interface AuthorityService {
    /**
     * 获取一个用户的权限列表
     * @param userId
     * @return
     */
    Set<Integer> getAuthorities(int userId);

}
