package com.zjicm.customkeyvalue.dao;

import com.zjicm.common.dao.PracticeBaseDaoImpl;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.customkeyvalue.domain.CustomKeyValue;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by yujing on 2017/5/13.
 */
@Repository
public class CustomKeyValueDaoImpl extends PracticeBaseDaoImpl<CustomKeyValue, Integer> implements CustomKeyValueDao {
    @Override
    public CustomKeyValue getByKey(String key) {
        if (StringUtils.isBlank(key)) return null;

        return getByField("keyStr", key);
    }

    @Override
    public boolean ifExistByKey(String key) {
        if (StringUtils.isBlank(key)) return false;

        List<Criterion> criterions = new ArrayList<>(2);
        criterions.add(Restrictions.eq("keyStr", key));
        int count = count(criterions);
        return count > 0;
    }

    @Override
    public PageResult<CustomKeyValue> page(int page, int size) {
        List<Order> orders = new ArrayList<>(1);
        orders.add(Order.desc("id"));

        return getPageResult(null, orders, page, size);
    }
}
