package com.zjicm.attachment.dao;

import com.zjicm.attachment.domain.Attachment;
import com.zjicm.attachment.enums.AttEnums;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.common.lang.sql.dao.BaseDAO;

import java.util.List;

/**
 * Created by yujing on 2017/4/15.
 */
public interface AttachmentDao extends BaseDAO<Attachment, Integer> {

    /**
     * 通过文件MD5获取对象
     *
     * @param md5 文件流 md5
     * @return
     */
    Attachment getByMd5(String md5);

    /**
     * 分页获取文件信息
     *
     * @param userId 用户id，<=0 时 不作为条件
     * @param types  文件类型，null 时 全部
     * @param page  页数
     * @param size 每页数量
     * @return
     */
    PageResult<Attachment> page(int userId, List<Integer> types, int page, int size);
}
