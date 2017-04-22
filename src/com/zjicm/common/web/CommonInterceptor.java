package com.zjicm.common.web;

import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.http.consts.HttpConsts;
import com.zjicm.common.lang.cookie.CookieHandler;
import com.zjicm.common.lang.http.util.WebUtil;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.util.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yujing on 2017/3/8.
 */
public class CommonInterceptor implements HandlerInterceptor {
    @Autowired
    @Qualifier("sessionCookieHandler")
    private CookieHandler sessionCookieHandler;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (handler != null && handler instanceof HandlerMethod &&
            ((HandlerMethod) handler).getBean() instanceof RootController) {

            RootController controller = (RootController) ((HandlerMethod) handler).getBean();

            // session 不存在时，尝试获取 cookie 中的登录 ID 信息
            if (request.getAttribute(HttpConsts.Request.ATTRIBUTES_SESSIONID) == null) {
                String sessionId = sessionCookieHandler.getCookieValue(request);


                if (StringUtils.isBlank(sessionId)) {
                    sessionId = UUIDUtil.get();
                    sessionCookieHandler.addCookie(response, sessionId);
                }

                request.setAttribute(HttpConsts.Request.ATTRIBUTES_SESSIONID, sessionId);
            }

            UserSession userSession = controller.getUserSession(request);

            // 登录限制
            if (controller.checkLogin() && !userSession.isLogin()) {
                controller.redirectWeb(response, "/login");
            }

            // 角色限制
            if (controller.roleToCheck() > 0)
                if (controller.roleToCheck() != userSession.getRoleId()) {
                    if (controller.isApi()) WebUtil.writeJson(response, new JsonDataHolder().error403().toJson());
                    else controller.redirectWeb(response, "/denied");
                    return false;
                }

            // 权限限制
            if (controller.permissionToCheck() > 0)
                if (userSession.getAuthorities() == null ||
                    !userSession.getAuthorities().contains(controller.permissionToCheck())) {
                    if (controller.isApi()) WebUtil.writeJson(response, new JsonDataHolder().error403().toJson());
                    else controller.redirectWeb(response, "/denied");
                    return false;
                }


        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o,
                                Exception e) throws Exception {

    }
}
