package com.zjicm.student.beans;

import com.zjicm.student.enums.StudentEnums;

/**
 * Created by yujing on 2017/4/14.
 */
public class StatusOut {
    private int status;
    private String statusStr;
    private int total;

    public StatusOut(StatusDto dto) {
        if (dto == null) return;
        StudentEnums.Status practiceStatus = StudentEnums.Status.is(dto.getStatus());
        if (practiceStatus == null) return;
        this.status = dto.getStatus();
        this.statusStr = practiceStatus.name();
        this.total = (int) dto.getTotal();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
