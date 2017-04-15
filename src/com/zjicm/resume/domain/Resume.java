package com.zjicm.resume.domain;

import com.zjicm.common.lang.sql.domain.CanonicalDomain;

import javax.persistence.*;
import java.util.Date;

/**
 * 简历数据域对象
 */
@Entity
@Table(name = "resume")
public class Resume implements CanonicalDomain<Integer> {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String studentNumber;
    private String title;
    private String majorClass;
    private String schoolExp;
    private String practiceExp;
    private String certificate;
    private String selfComment;
    private String worksUrl;
    private String resumeUrl;
    @Column(updatable = false)
    private int creator;
    private int modifier;
    @Column(updatable = false)
    private Date createTime;                            // 创建时间
    private Date modifyTime;                            // 修改时间



    public Resume() {
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


    public String getStudentNumber() {
        return studentNumber;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }


    public String getMajorClass() {
        return this.majorClass;
    }

    public void setMajorClass(String majorClass) {
        this.majorClass = majorClass;
    }

    public String getSchoolExp() {
        return this.schoolExp;
    }

    public void setSchoolExp(String schoolExp) {
        this.schoolExp = schoolExp;
    }

    public String getPracticeExp() {
        return this.practiceExp;
    }

    public void setPracticeExp(String practiceExp) {
        this.practiceExp = practiceExp;
    }

    public String getCertificate() {
        return this.certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getSelfComment() {
        return this.selfComment;
    }

    public void setSelfComment(String selfComment) {
        this.selfComment = selfComment;
    }

    public String getWorksUrl() {
        return this.worksUrl;
    }

    public void setWorksUrl(String worksUrl) {
        this.worksUrl = worksUrl;
    }

    public String getResumeUrl() {
        return this.resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
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