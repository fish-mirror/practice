package com.zjicm.shortterm.enums;

/**
 * 短学期相关枚举
 *<p>
 * Created by yujing on 2017/5/11.
 */
public class ShortTermEnums {
    public enum ProjectStatus {
        cancel(0),                  // 关闭选课
        can_selected(1),            // 可选课
        can_upload_report(2),       // 可上传
        can_grade(3);               // 可评分

        private int value;

        ProjectStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static ProjectStatus is(int value) {
            for (ProjectStatus status : ProjectStatus.values()) {
                if (status.getValue() == value) return status;
            }
            return null;
        }
    }
    public enum ProjectFull {
        full(1),             // 已选完
        unfull(2);           // 未完可选

        private int value;

        ProjectFull(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static ProjectFull is(int value) {
            for (ProjectFull status : ProjectFull.values()) {
                if (status.getValue() == value) return status;
            }
            return null;
        }
    }

}
