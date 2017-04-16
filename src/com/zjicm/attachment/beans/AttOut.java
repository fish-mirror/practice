package com.zjicm.attachment.beans;

import com.zjicm.attachment.domain.Attachment;

import java.io.Serializable;

/**
 * 附件输出信息
 * <p/>
 * Created by yujing on 2017/4/15.
 */
public class AttOut implements Serializable {

    public AttOut() {
    }

    public AttOut(Attachment att) {
        if (att == null) return;

        this.id = att.getId();
        this.originalName = att.getOriginalName();
        this.size = att.getSize();
        this.path = att.getPath();
        }

    private Integer id;
    private String originalName;                    // 原始上传文件名
    private Integer size;                           // 文件字节数
    private String path;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
