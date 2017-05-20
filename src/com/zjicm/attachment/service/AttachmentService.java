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

    /**
     * 通过 ID 获取
     *
     * @param id
     * @param userId
     * @return
     */
    public Attachment getById(int id, int userId) {
        Attachment attachment = attachmentDao.getById(id);
        if (attachment == null) return null;

        AttEnums.Type type = AttEnums.Type.is(attachment.getObjectType());
        if (type == null) return null;

        switch (type) {
            case avatar:
            case news_picture:
            case short_term_introduce:
                return attachment;
            case short_term_report:
            case practice_report:
                return attachment;

            default:
                if (attachment.getUserId() == userId) return attachment;
                break;
        }
        return null;
    }

    /**
     * 多 ID 获取
     *
     * @param ids
     * @param userId
     * @return
     */
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
                case short_term_introduce:
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
     * 获取自己上传文件的分页信息
     *
     * @param userId
     * @param type
     * @param page
     * @param size
     * @return
     */
    public PageResult<Attachment> pageByUser(int userId, AttEnums.Type type, int page, int size) {
        List<Integer> types = null;
        if (type != null) {
            types = new ArrayList<>();
            types.add(type.getValue());
        }
        return attachmentDao.page(userId, types, page, size);
    }

    /**
     * 获取公开文件的分页信息
     *
     * @param page
     * @param size
     * @return
     */
    public PageResult<Attachment> pagePublic(int page, int size) {
        List<Integer> types = new ArrayList<>();
        types.add(AttEnums.Type.excel.getValue());
        types.add(AttEnums.Type.word.getValue());
        types.add(AttEnums.Type.short_term_introduce.getValue());


        return attachmentDao.page(0, types, page, size);
    }


}
