package com.zjicm.cooperation.domain;

import com.zjicm.common.lang.sql.domain.CanonicalDomain;

import javax.persistence.*;
import java.util.Date;

/**
 * 校企合作意愿数据域对象
 */
@Entity
@Table(name = "intention")
public class Intention implements CanonicalDomain<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int institute;
    private String title;
    private String content;

    @Column(updatable = false)
    private int creator;
    private int modifier;
    @Column(updatable = false)
    private Date createTime;                            // 创建时间
    private Date modifyTime;                            // 修改时间


    public Intention() {
    }


    public Integer getId() {
        return this.id;
    }

    @Override
    public void prepare() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getInstitute() {
        return institute;
    }

    public void setInstitute(int institute) {
        this.institute = institute;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
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
}