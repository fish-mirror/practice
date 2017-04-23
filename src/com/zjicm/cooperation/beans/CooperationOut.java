package com.zjicm.cooperation.beans;

import com.zjicm.cooperation.domain.Cooperation;
import com.zjicm.student.support.CollegeInfoSupport;


/**
 * 校企合作信息输出对象
 * <p>
 * Created by yujing on 2017/4/23.
 */
public class CooperationOut {
    private int id;
    private String instituteName;
    private String companyName;
    private long createTimestamp;

    public CooperationOut(Cooperation cooperation) {
        if (cooperation == null) return;
        this.id = cooperation.getId();
        this.instituteName = CollegeInfoSupport.getInstitute(cooperation.getInstitute());
        this.createTimestamp = cooperation.getCreateTime().getTime();
        if (cooperation.getCompany() != null)  this.companyName = cooperation.getCompany().getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
