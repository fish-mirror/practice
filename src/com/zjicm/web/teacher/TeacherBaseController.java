package com.zjicm.web.teacher;

import com.zjicm.auth.enums.Role;
import com.zjicm.common.web.RootController;

/**
 * 教师接口／页面访问基础类
 * <p>
 * Created by yujing on 2017/4/14.
 */
public class TeacherBaseController extends RootController {
    @Override
    public boolean isApi() {
        return true;
    }

    @Override
    public boolean checkLogin() {
        return true;
    }

    @Override
    public int roleToCheck() {
        return Role.teacher.getValue();
    }

}
