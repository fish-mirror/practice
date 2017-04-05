package com.zjicm.shortterm.domain;

import com.zjicm.auth.domain.User;
import com.zjicm.common.lang.sql.domain.CanonicalDomain;

import javax.persistence.*;
import java.util.Date;

/**
 * 短学期作品评分数据域对象
 */

@Entity
@Table(name = "short_term_comment")
public class ShortTermComment implements CanonicalDomain<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int rid;
    private int teacherUserId;
    private String studentNumber;
    private String comment;
    private float grade;
    @Column(updatable = false)
    private Date createTime;                            // 创建时间
    private Date modifyTime;                            // 修改时间


    public ShortTermComment() {
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

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getTeacherUserId() {
        return teacherUserId;
    }

    public void setTeacherUserId(int teacherUserId) {
        this.teacherUserId = teacherUserId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
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