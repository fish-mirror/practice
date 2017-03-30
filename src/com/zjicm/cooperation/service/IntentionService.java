package com.zjicm.cooperation.service;

import java.util.List;

import com.zjicm.common.lang.page.PageResult;
import com.zjicm.cooperation.domain.Intention;

public interface IntentionService {

    void save(Intention intention);

    void delete(Intention intention);

    Intention get(int id);

    PageResult<Intention> page(int institute, int page, int size);

}
