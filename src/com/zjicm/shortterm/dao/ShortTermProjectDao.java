package com.zjicm.shortterm.dao;

import com.zjicm.common.lang.sql.dao.BaseDAO;
import com.zjicm.shortterm.domain.ShortTermProject;

import java.util.List;

public interface ShortTermProjectDao extends BaseDAO<ShortTermProject, Integer> {

    /**
     * 获取可选的项目列表
     *
     * @param institute
     * @param term
     * @return
     */
    List<ShortTermProject> getAllCanSelected(int institute, String term);
}
