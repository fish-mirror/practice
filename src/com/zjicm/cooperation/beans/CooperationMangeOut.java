package com.zjicm.cooperation.beans;


import com.zjicm.cooperation.domain.Cooperation;

/**
 * 校企合作管理信息输出对象
 * <p>
 * Created by yujing on 2017/4/23.
 */
public class CooperationMangeOut extends CooperationOut{
    private int status;

    public CooperationMangeOut(Cooperation cooperation) {
        super(cooperation);
        this.status = cooperation.getStatus();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
