package com.zjicm.web.company;

import com.zjicm.auth.enums.Role;
import com.zjicm.common.web.RootController;

/**
 * 企业接口／页面访问基础类
 *
 * Created by yujing on 2017/4/14.
 */
public class CompanyBaseController extends RootController {
    @Override
    public int roleToCheck() {
        return Role.company.getValue();
    }
}
