package com.zjicm.auth.web;

import com.zjicm.auth.beans.LoginView;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.JsonErrorInfo;
import com.zjicm.common.web.RootController;
import com.zjicm.auth.domain.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 统一账号管理
 * <p>
 * Created by yujing on 2017/3/8.
 */
@Controller
@RequestMapping(value = "/auth/account")
public class AuthAccountController extends RootController {

    /**
     * 统一帐号登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        @Valid @ModelAttribute("formBean") LoginView loginView,
                        BindingResult results,
                        ModelMap mmap
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        List<JsonErrorInfo> errorInfos = new ArrayList<>();
        jsonDataHolder.packErrorsFromResult(400, "验证失败", errorInfos, results);

        if (CollectionUtils.isNotEmpty(errorInfos)) {
            mmap.put("errors", errorInfos);
            return VIEW_LOGIN;
        }

        User user = userDao.getByNumPwd(loginView.getAccount(), loginView.getPassword());
        if (user == null) {
            mmap.put("error", "账号和密码不匹配");
            return VIEW_LOGIN;
        }

        UserSession userSession = doLogin(request, response, user.getId());
        if (userSession == null) {
            mmap.put("error", "该账号状态异常");
            return VIEW_LOGIN;
        }
        mmap.put("session", userSession);
        return userSession.getIndexPage();
    }

    /**
     * 登出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout(HttpServletRequest request,
                                 HttpServletResponse response
    ) {
        UserSession userSession = getUserSession(request);
        if (userSession.isLogin()) {
            doLogout(userSession, response);
        }
        return VIEW_INDEX;
    }
}
