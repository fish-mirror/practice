package com.zjicm.web.student;

import com.zjicm.auth.enums.Role;
import com.zjicm.common.web.RootController;

/**
 * 学生接口／页面访问基础类
 *
 * Created by yujing on 2017/4/14.
 */
public class StudentBaseController extends RootController {
    @Override
    public int roleToCheck() {
        return Role.student.getValue();
    }
}
