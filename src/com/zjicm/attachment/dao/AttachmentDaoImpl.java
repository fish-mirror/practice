package com.zjicm.attachment.dao;

import com.zjicm.attachment.domain.Attachment;
import com.zjicm.attachment.enums.AttEnums;
import com.zjicm.common.dao.PracticeBaseDaoImpl;
import com.zjicm.common.lang.page.PageResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yujing on 2017/4/15.
 */
@Repository
public class AttachmentDaoImpl extends PracticeBaseDaoImpl<Attachment, Integer> implements AttachmentDao {

    @Override
    public Attachment getByMd5(String md5) {
        if (StringUtils.isBlank(md5)) return null;
        return this.getByField("md5", md5);
    }

    @Override
    public PageResult<Attachment> page(int userId, List<Integer> types, int page, int size) {
        List<Criterion> criterions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(types)) {
            criterions.add(Restrictions.in("objectType", types));
        }
        if (userId > 0) {
            criterions.add(Restrictions.eq("userId", userId));
        }
        return this.getPageResult(criterions, null, page, size);
    }
}
