package com.zjicm.student.enums;

/**
 * Created by yujing on 2017/4/12.
 */
public class StudentEnums {
    public enum Status {
        no_practice(0),            // 尚未实习
        practicing(1),             // 实习中
        practiced(2),              // 实习完成
        practice_graded(3);        // 实习完成并已评分

        private int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Status is(int value) {
            for (Status status : Status.values()) {
                if (status.getValue() == value) return status;
            }
            return null;
        }
    }
}
