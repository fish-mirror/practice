package com.zjicm.web.teacher;

import com.zjicm.auth.enums.Role;
import com.zjicm.common.web.RootController;

/**
 * 教师接口／页面访问基础类
 *
 * Created by yujing on 2017/4/14.
 */
public class TeacherBaseController extends RootController {
    @Override
    public int roleToCheck() {
        return Role.teacher.getValue();
    }
}
