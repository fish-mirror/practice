package com.zjicm.shortterm.domain;

import com.zjicm.common.lang.sql.domain.CanonicalDomain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 短学期报告数据域对象
 */

@Entity
@Table(name = "short_term_report")
public class ShortTermReport implements CanonicalDomain<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String studentNumber;
    private int projectId;
    private int attId;

    @Column(updatable = false)
    private Date createTime;                            // 创建时间
    private Date modifyTime;                            // 修改时间

    public ShortTermReport() {
    }


    public Integer getId() {
        return id;
    }

    @Override
    public void prepare() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getAttId() {
        return attId;
    }

    public void setAttId(int attId) {
        this.attId = attId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}