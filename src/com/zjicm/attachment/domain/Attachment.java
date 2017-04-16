package com.zjicm.attachment.domain;

import com.zjicm.common.lang.sql.domain.CanonicalDomain;

import javax.persistence.*;
import java.util.Date;

/**
 * 文件信息
 * <p/>
 * Created by yujing on 2017/4/15.
 */
@Entity
@Table(name = "attachment")
public class Attachment implements CanonicalDomain<Integer> {

    private static final long serialVersionUID = -1517487748522410814L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;                            // 原始文件名(xxx.ext)
    private String path;                            // 上传后的文件路径(/path/xxx.ext)
    private String mime;                            // http 文件格式信息
    private int size;                               // 文件字节数
    private int userId;                             // 用户ID
    private int width;                              // 宽度
    private int height;                             // 高度
    private int objectType;                         // 关联对象类型
    private int objectId;                           // 关联对象ID
    private boolean embed;                          // 可以直接展示的附件
    private boolean pub;                            // 是否公开标记
    private String uid;                             // 文件ID，由随机数组成
    private String ext;                             // 文件扩展名，不带点
    private String md5;                             // 文件的MD5值
    private String cropImg;                         // 裁剪图信息集合
    private String originalName;                    // 原始上传文件名
    private String originalPath;                    // 原始上传后的文件路径（不带文件名）
    private String info;                            // 文件信息

    @Column(updatable = false)
    private Date createTime;                        // 创建时间
    private Date modifyTime;                        // 修改时间

    @Override
    public void prepare() {

    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getModifyTime() {
        return modifyTime;
    }

    @Override
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public boolean isEmbed() {
        return embed;
    }

    public void setEmbed(boolean embed) {
        this.embed = embed;
    }

    public boolean isPub() {
        return pub;
    }

    public void setPub(boolean pub) {
        this.pub = pub;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getCropImg() {
        return cropImg;
    }

    public void setCropImg(String cropImg) {
        this.cropImg = cropImg;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}