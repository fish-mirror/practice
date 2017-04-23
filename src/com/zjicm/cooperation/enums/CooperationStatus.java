package com.zjicm.cooperation.enums;

/**
 * 校企合作状态
 * <p>
 * Created by yujing on 2017/4/23.
 */
public enum CooperationStatus {
    init(0),
    cooperating(1),
    cancel(2);


    private int value;

    CooperationStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CooperationStatus is(int value) {
        for (CooperationStatus status : CooperationStatus.values()) {
            if (status.getValue() == value) return status;
        }
        return null;
    }
}
