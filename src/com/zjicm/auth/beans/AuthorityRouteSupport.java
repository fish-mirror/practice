package com.zjicm.auth.beans;

import com.zjicm.auth.enums.AuthEnums;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 菜单路径输出
 * Created by yujing on 2017/5/11.
 */
public class AuthorityRouteSupport {
    private static List<RoutesOut> companyRoutes = new ArrayList<>();
    private static List<RoutesOut> studentRoutes = new ArrayList<>();

    static {

        // 企业
        companyRoutes.add(new RoutesOut("校企合作",
                                        new RouteBean("合作意向", "/intention/list"),
                                        new RouteBean("合作学院", "/cooperation/list"),
                                        new RouteBean("短学期项目", "/short_term/list")));
        companyRoutes.add(new RoutesOut("实习生管理",
                                        new RouteBean("实习生列表", "/student/list"),
                                        new RouteBean("实习周记", "/practice/weekly"),
                                        new RouteBean("短学期报告")));
        companyRoutes.add(new RoutesOut("职位管理",
                                        new RouteBean("职位列表", "/job/list"),
                                        new RouteBean("发布职位", "/job/create"),
                                        new RouteBean("求职申请", "/job/apply")));

        // 学生
        studentRoutes.add(new RoutesOut("个人信息管理",
                                        new RouteBean("个人信息", "/info"),
                                        new RouteBean("简历列表", "/resume/list"),
                                        new RouteBean("新增简历", "/resume/create")));
        studentRoutes.add(new RoutesOut("短学期",
                                        new RouteBean("实践记录", "/short_term/selected"),
                                        new RouteBean("选课列表", "/short_term/list")));


    }


    /**
     * 获取企业用户的菜单列表
     *
     * @return
     */
    public static List<RoutesOut> getCompanyRoutes() {

        return companyRoutes;
    }

    /**
     * 获取学生用户的菜单列表
     *
     * @return
     */
    public static List<RoutesOut> getStudentRoutes() {
        return studentRoutes;
    }

    /**
     * 获取教师用户的菜单列表
     *
     * @param authorities
     * @return
     */
    public static List<RoutesOut> getTeacherRoutes(Set<Integer> authorities) {
        List<RoutesOut> routesOuts = new ArrayList<>();

        // 学生管理
        RoutesOut studentMR = new RoutesOut("学生管理",
                                            new RouteBean("学生列表", "/student/list"),
                                            new RouteBean("数据统计", "/practice/data"),
                                            new RouteBean("实习周记", "/practice/weekly"));
        if (authorities.contains(AuthEnums.practice_manage.getValue())) {
            studentMR.addRoute(new RouteBean("实习申请列表", "/practice/apply"));
        }
        routesOuts.add(studentMR);

        // 校企合作
        RoutesOut cooperationMR = new RoutesOut("校企合作",
                                                new RouteBean("合作意向列表", "/intention/list"),
                                                new RouteBean("合作企业列表", "/cooperation/list"));
        if (authorities.contains(AuthEnums.cooperation_manage.getValue())) {
            studentMR.addRoute(new RouteBean("创建合作企业", "/company/create"));
        }
        studentMR.addRoute(new RouteBean("短学期管理", "/short_term/list"));

        if (authorities.contains(AuthEnums.short_term_project_manage.getValue())) {
            studentMR.addRoute(new RouteBean("编辑短学期项目", "/short_term/edit"));
        }
        routesOuts.add(cooperationMR);

        // 职位管理
        RoutesOut jobMR = new RoutesOut("职位管理",
                                        new RouteBean("职位列表", "/job/list"));
        if (authorities.contains(AuthEnums.job_manage.getValue())) {
            studentMR.addRoute(new RouteBean("发布职位", "/job/create"));
            studentMR.addRoute(new RouteBean("求职申请", "/job/apply"));

        }
        routesOuts.add(jobMR);

        // 资讯管理
        if (authorities.contains(AuthEnums.news_manage.getValue())) {
            routesOuts.add(new RoutesOut("资讯管理",
                                         new RouteBean("资讯列表", "/news/list"),
                                         new RouteBean("新增", "/news/create")));
        }

        // 后台管理
        if (authorities.contains(AuthEnums.system.getValue())) {
            routesOuts.add(new RoutesOut("后台管理",
                                         new RouteBean("院系账号列表", "/admin/account"),
                                         new RouteBean("设置权限", "/admin/authority"),
                                         new RouteBean("重置密码", "/admin/resetpwd")));
        }
        return routesOuts;
    }
}
