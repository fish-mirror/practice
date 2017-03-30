package com.zjicm.cooperation.service;

import com.zjicm.common.lang.page.PageResult;
import com.zjicm.cooperation.domain.Cooperation;

public interface CooperationService {

    void save(Cooperation cooperate);
    
    void delete(Cooperation cooperate);

    Cooperation get(int id);

    PageResult<Cooperation> page(int institute, int page, int size);

}
