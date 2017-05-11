package com.zjicm.auth.enums;

/**
 * 权限枚举
 *
 * Created by yujing on 2017/3/19.
 */
public enum AuthEnums {
    student_manage(100),                 // 学生信息管理权限
    practice_manage(200),                // 学生实习管理权限
    short_term_manage(300),              // 学生短学期实习管理权限
    short_term_project_manage(400),      // 短学期课程管理权限
    cooperation_manage(500),             // 校企合作管理权限
    intention_manage(510),               // 合作意向管理权限
    job_manage(600),                     // 职位管理

    institute_user_manage(700),          // 本学院用户权限管理
    college_user_manage(710),            // 跨学院权限管理

    news_manage(800),                    // 资讯管理


    system(900),                        // 系统管理
    super_system(999);                  // 所有权限

    private int value;

    AuthEnums(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AuthEnums is(int value){
        for (AuthEnums authEnums : AuthEnums.values()) {
            if (authEnums.getValue() == value) return authEnums;
        }
        return null;
    }
}
