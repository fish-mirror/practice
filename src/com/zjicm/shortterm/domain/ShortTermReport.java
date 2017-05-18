package com.zjicm.shortterm.domain;

import com.zjicm.common.lang.sql.domain.CanonicalDomain;
import com.zjicm.student.domain.Student;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentNumber", referencedColumnName = "number")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private ShortTermProject project;
    private int attId;
    private int type;

    @Column(updatable = false)
    private Date createTime;                            // 创建时间
    private Date modifyTime;                            // 修改时间

    @OneToMany(mappedBy = "reportId", fetch = FetchType.EAGER)
    private Set<ShortTermComment> comments;

    public ShortTermReport() {
    }

    public ShortTermReport(Student student, ShortTermProject project, int type) {
        this.student = student;
        this.project = project;
        this.type = type;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ShortTermProject getProject() {
        return project;
    }

    public void setProject(ShortTermProject project) {
        this.project = project;
    }

    public int getAttId() {
        return attId;
    }

    public void setAttId(int attId) {
        this.attId = attId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Set<ShortTermComment> getComments() {
        return comments;
    }

    public void setComments(Set<ShortTermComment> comments) {
        this.comments = comments;
    }

    public int getAverageScore() {
        if (CollectionUtils.isEmpty(comments)) return 0;

        int sum = 0;
        for (ShortTermComment comment : comments) sum += comment.getScore();
        return sum / comments.size();
    }
}