package com.zjicm.practice.domain;

import com.zjicm.common.lang.sql.domain.CanonicalDomain;
import com.zjicm.shortterm.domain.ShortTermComment;
import com.zjicm.student.domain.Student;
import org.apache.commons.collections.CollectionUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 实习信息数据域对象
 * <p>
 * Created by yujing on 2017/5/20.
 */
@Entity
@Table(name = "practice_info")
public class PracticeInfo implements CanonicalDomain<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "studentNumber", referencedColumnName = "number")
    private Student student;

    private String companyNumber;
    private String companyName;
    private String job;
    private String linkman;
    private String cellphone;
    private String address;
    private String province;
    private String city;

    private String purpose;
    private int status;
    private int attId;

    @OneToMany(mappedBy = "practiceId", fetch = FetchType.EAGER)
    private Set<PracticeComment> comments;

    private int creator;
    private int modifier;

    @Column(updatable = false)
    private Date createTime;                            // 创建时间
    private Date modifyTime;                            // 修改时间


    public Integer getId() {
        return id;
    }

    @Override
    public void prepare() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAttId() {
        return attId;
    }

    public Set<PracticeComment> getComments() {
        return comments;
    }

    public void setComments(Set<PracticeComment> comments) {
        this.comments = comments;
    }

    public int getAverageScore() {
        if (CollectionUtils.isEmpty(comments)) return 0;

        int sum = 0;
        for (PracticeComment comment : comments) sum += comment.getScore();
        return sum / comments.size();
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
