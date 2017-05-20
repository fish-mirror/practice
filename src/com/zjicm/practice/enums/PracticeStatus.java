package com.zjicm.practice.enums;

/**
 * 实习操作状态
 * <p>
 * Created by yujing on 2017/5/20.
 */
public enum PracticeStatus {
    unreview(0),                  // 待审核
    ok(1),                        // 正常状态
    rejected(2),                  // 已拒绝
    cancel(3),                    // 已取消
    can_score(4),                 // 可评分
    finished(5);                  // 已完成

    private int value;

    PracticeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PracticeStatus is(int value) {
        for (PracticeStatus status : PracticeStatus.values()) {
            if (status.getValue() == value) return status;
        }
        return null;
    }
}
