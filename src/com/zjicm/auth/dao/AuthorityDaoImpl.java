package com.zjicm.auth.dao;

import com.zjicm.auth.domain.Authority;
import com.zjicm.common.dao.PracticeBaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDaoImpl extends PracticeBaseDaoImpl<Authority, Integer> implements AuthorityDao {

}
