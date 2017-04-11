package com.zjicm.common.web;

import com.zjicm.auth.domain.User;
import com.zjicm.auth.enums.Role;
import com.zjicm.auth.service.AuthorityService;
import com.zjicm.auth.service.UserService;
import com.zjicm.cache.consts.CacheConsts;
import com.zjicm.cache.servive.CacheService;
import com.zjicm.common.Environment;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.http.consts.HttpConsts;
import com.zjicm.common.lang.cookie.CookieHandler;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.company.domain.Company;
import com.zjicm.company.service.CompanyService;
import com.zjicm.student.domain.Student;
import com.zjicm.student.service.StudentService;
import com.zjicm.teacher.domain.Teacher;
import com.zjicm.teacher.service.TeacherService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 基类统一控制器
 * <p>
 * Created by yujing on 2017/3/8.
 */
public class RootController {
    protected final static String VIEW_LOGIN = "accoount.login";
    protected final static String VIEW_INDEX = "view.index";
    protected final static String VIEW_STUDENT_INDEX = "student.index";
    protected final static String VIEW_TEACHER_INDEX = "teacher.index";
    protected final static String VIEW_COMPANY_INDEX = "company.index";

    @Autowired
    protected UserService userService;
    @Autowired
    protected AuthorityService authorityService;
    @Autowired
    protected StudentService studentService;
    @Autowired
    protected TeacherService teacherService;
    @Autowired
    protected CompanyService companyService;
    @Autowired
    protected CacheService cacheService;
    @Autowired
    @Qualifier("sessionCookieHandler")
    protected CookieHandler sessionCookieHandler;

    /**
     * 用户登录逻辑
     * <p/>
     * 得到用户信息，放到 session 中，并生成一个 cookie 用以判断保持登录状态，
     * 并更新用户 session 在 memcache 中的状态
     * <p/>
     * 并更新最后登录时间
     * <p/>
     * <p/>
     *
     * @param request
     * @param response
     * @param userId   用户ID
     * @return 一个用户 session 信息对象
     */
    protected UserSession doLogin(HttpServletRequest request,
                                  HttpServletResponse response,
                                  int userId) {
        if (userId <= 0) return null;

        UserSession userSession = getUserSession(request);
        User user = userService.get(userId);
        if (user == null) return null;

        Role role = Role.is(user.getRoleId());
        if (role == null) return null;


        switch (role) {
            case teacher:
                Teacher teacher = teacherService.getByNum(user.getNumber());
                userSession.set(user, teacher);
                userSession.setIndexPage(VIEW_TEACHER_INDEX);
                break;
            case student:
                Student student = studentService.getByNum(user.getNumber());
                userSession.set(user, student);
                userSession.setIndexPage(VIEW_STUDENT_INDEX);
                break;
            case company:
                Company company = companyService.getByNum(user.getNumber());
                userSession.set(user, company);
                userSession.setIndexPage(VIEW_COMPANY_INDEX);
                break;
            default:
                return null;
        }
        Set<Integer> authorities = authorityService.getAuthorities(userId);
        if (CollectionUtils.isNotEmpty(authorities)) userSession.setAuthorities(authorities);
        return userSession;
    }

    public void doLogout(UserSession userSession, HttpServletResponse response) {
        cacheService.clear(CacheConsts.Storage.USERSESSION, userSession.getId());
        sessionCookieHandler.removeCookie(response);
    }

    /**
     * 获取 userSession
     *
     * @param request
     * @return
     */
    public UserSession getUserSession(HttpServletRequest request) {
        UserSession userSession = (UserSession) request.getAttribute(HttpConsts.Request.ATTRIBUTES_USERSESSION);
        if (userSession == null) {

            String sessionId = (String) request.getAttribute(HttpConsts.Request.ATTRIBUTES_SESSIONID);
            // 从 USERSESSION 中获取得到 session 时，会重新更新缓存时间
            userSession = cacheService.get(CacheConsts.Storage.USERSESSION, sessionId);
            if (userSession == null) userSession = new UserSession(sessionId);
            request.setAttribute(HttpConsts.Request.ATTRIBUTES_USERSESSION, userSession);
        }
        refreshSession(userSession);
        return userSession;
    }

    public void refreshSession(UserSession userSession) {
        cacheService.set(CacheConsts.Storage.USERSESSION, userSession.getId(),userSession, 0);
    }

    public JsonDataHolder redirect(HttpServletResponse response, String service) {
        try {
            response.sendRedirect(StringUtils.defaultIfBlank(service, Environment.WEBINDEX));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public String redirectWeb(HttpServletResponse response, String service) {
        try {
            response.sendRedirect(StringUtils.defaultIfBlank(service, Environment.WEBINDEX));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkLogin() {
        return true;
    }

    public boolean isApi() {
        return false;
    }

    public long permissionToCheck() {
        return 0l;
    }

    public int statusToCheck() {
        return 0;
    }
}
