package com.zjicm.web.admin;

import com.zjicm.auth.enums.AuthEnums;
import com.zjicm.auth.enums.Role;
import com.zjicm.common.web.RootController;

/**
 * 后台管理基类
 *
 * Created by yujing on 2017/5/13.
 */
public class AdminBaseController extends RootController {
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

    @Override
    public int permissionToCheck() {
        return AuthEnums.system.getValue();
    }
}
