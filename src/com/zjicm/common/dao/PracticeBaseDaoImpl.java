package com.zjicm.common.dao;

import com.zjicm.common.lang.sql.dao.BaseDAOImpl;
import com.zjicm.common.lang.sql.domain.CanonicalDomain;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;

/**
 * Created by yujing on 2017/3/8.
 */
public class PracticeBaseDaoImpl<V extends CanonicalDomain<K>, K extends Serializable> extends BaseDAOImpl<V, K> {

    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory0(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

}
