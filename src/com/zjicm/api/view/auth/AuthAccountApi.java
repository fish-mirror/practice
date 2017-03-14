package com.zjicm.api.view.auth;

import com.dxy.base.util.CollectionUtil;
import com.zjicm.auth.beans.LoginView;
import com.zjicm.auth.domain.User;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.JsonErrorInfo;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.common.web.RootController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 统一账号接口
 * <p>
 * Created by yujing on 2017/3/8.
 */
@Controller
@RequestMapping(value = "/auth/i/account")
public class AuthAccountApi extends RootController {

    /**
     * 统一帐号登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder login(HttpServletRequest request,
                                HttpServletResponse response,
                                @Valid @ModelAttribute("formBean") LoginView loginView,
                                BindingResult results
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        List<JsonErrorInfo> errorInfos = new ArrayList<>();
        jsonDataHolder.packErrorsFromResult(400, "验证失败", errorInfos, results);

        if (CollectionUtil.isNotEmpty(errorInfos)) return jsonDataHolder;

        User user = userService.search(loginView.getAccount(), loginView.getPassword());

        if (user == null) return jsonDataHolder.putToError(404, "账号和密码不匹配");

        UserSession userSession = doLogin(request, response, user.getId());
        if (userSession == null) return jsonDataHolder.putToError(404, "该账号状态异常");

        return redirect(response, "");
    }

    /**
     * 统一登出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder logout(HttpServletRequest request,
                                 HttpServletResponse response
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession userSession = getUserSession(request);
        if (userSession.isLogin()) {
            doLogout(userSession, response);
        }
        return jsonDataHolder.simpleMsg(0, MsgType.update);
    }
}
