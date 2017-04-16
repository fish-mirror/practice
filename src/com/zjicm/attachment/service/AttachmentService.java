package com.zjicm.attachment.service;

import com.zjicm.attachment.dao.AttachmentDao;
import com.zjicm.attachment.domain.Attachment;
import com.zjicm.attachment.enums.AttEnums;
import com.zjicm.common.lang.page.PageResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 附件逻辑
 * <p/>
 * Created by yujing on 2017/4/15.
 */
@Service
public class AttachmentService {


    @Autowired
    private AttachmentDao attachmentDao;



    public List<Attachment> getListByAuth(List<Integer> ids, int userId) {
        List<Attachment> attachments = attachmentDao.getByIds(ids);
        if (CollectionUtils.isEmpty(attachments)) return null;

        List<Attachment> list = new ArrayList();
        attachments.forEach(attachment -> {
            AttEnums.Type type = AttEnums.Type.is(attachment.getObjectType());
            if (type == null) return;

            switch (type) {
                case avatar:
                case news_picture:
                    list.add(attachment);
                    break;
                default:
                    if (attachment.getUserId() == userId) list.add(attachment);
                    break;
            }
        });
        return list;
    }


    /**
     * 获取文件的分页信息
     *
     * @param userId
     * @param type
     * @param page
     * @param size
     * @return
     */
    public PageResult<Attachment> page(int userId, AttEnums.Type type, int page, int size) {
        return attachmentDao.page(userId, type, page, size);
    }


}
