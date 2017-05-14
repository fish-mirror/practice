package com.zjicm.shortterm.beans;

import com.zjicm.common.lang.util.NumberUtil;
import com.zjicm.student.support.CollegeInfoSupport;

/**
 * 短学期是否开启选课开关的输出
 * <p>
 * Created by yujing on 2017/5/14.
 */
public class ShorTermAdminOut {
    private int institute;
    private String instituteName;
    private boolean open;

    public ShorTermAdminOut(String institute, boolean open) {
        this.institute = NumberUtil.parseIntQuietly(institute);
        this.instituteName = CollegeInfoSupport.getInstitute(this.institute);
        this.open = open;
    }

    public int getInstitute() {
        return institute;
    }

    public void setInstitute(int institute) {
        this.institute = institute;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
