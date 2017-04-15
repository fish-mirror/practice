package com.zjicm.auth.enums;

/**
 * 角色类型
 *
 * Created by yujing on 2017/3/10.
 */
public enum Role {
    teacher(1),
    student(2),
    company(3);

    private int value;

    Role(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Role is(int value){
        for (Role role : Role.values()) {
            if (role.getValue() == value) return role;
        }
        return null;
    }

}
