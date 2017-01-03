package com.zjicm.practice.service;

import com.zjicm.dto.Page;
import com.zjicm.entity.PracticeData;

public interface IPracticeDataService {


    /**
     * 查看实习数据的分页信息
     *
     * @param pageSize
     * @param page
     * @return
     */
    public Page pageForPracticeData(int pageSize, int page);

    public void add(PracticeData pd);
}
