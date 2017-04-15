package com.zjicm.shortterm.domain;

import com.zjicm.common.lang.sql.domain.CanonicalDomain;

import javax.persistence.*;
import java.util.Date;

/**
 * 短学期项目数据域对象
 */
@Entity
@Table(name = "short_term_project")
public class ShortTermProject implements CanonicalDomain<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String companyUserId;

    private int institute;
    private String purpose;
    private String content;
    private String majorNeed;
    private String gradeNeed;
    private Integer selectedNum;
    private Integer topNum;
    private Integer unmajorNum;
    private Integer unmajorSelected;
    private String term;
    private String place;
    private String time;
    private int status;
    private String fileUrl;
    private int attId;
    @Column(updatable = false)
    private int creator;
    private int modifier;
    @Column(updatable = false)
    private Date createTime;                            // 创建时间
    private Date modifyTime;                            // 修改时间



    public ShortTermProject() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(String companyUserId) {
        this.companyUserId = companyUserId;
    }

    public int getInstitute() {
        return institute;
    }

    public void setInstitute(int institute) {
        this.institute = institute;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMajorNeed() {
        return majorNeed;
    }

    public void setMajorNeed(String majorNeed) {
        this.majorNeed = majorNeed;
    }

    public String getGradeNeed() {
        return gradeNeed;
    }

    public void setGradeNeed(String gradeNeed) {
        this.gradeNeed = gradeNeed;
    }

    public Integer getSelectedNum() {
        return selectedNum;
    }

    public void setSelectedNum(Integer selectedNum) {
        this.selectedNum = selectedNum;
    }

    public Integer getTopNum() {
        return topNum;
    }

    public void setTopNum(Integer topNum) {
        this.topNum = topNum;
    }

    public Integer getUnmajorNum() {
        return unmajorNum;
    }

    public void setUnmajorNum(Integer unmajorNum) {
        this.unmajorNum = unmajorNum;
    }

    public Integer getUnmajorSelected() {
        return unmajorSelected;
    }

    public void setUnmajorSelected(Integer unmajorSelected) {
        this.unmajorSelected = unmajorSelected;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getAttId() {
        return attId;
    }

    public void setAttId(int attId) {
        this.attId = attId;
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