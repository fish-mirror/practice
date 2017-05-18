package com.zjicm.api.view.auth;

import com.zjicm.auth.beans.LoginParam;
import com.zjicm.auth.domain.User;
import com.zjicm.common.beans.KeyValue;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.JsonErrorInfo;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.common.web.RootController;
import com.zjicm.company.domain.Company;
import com.zjicm.cooperation.beans.CompanyRegisterParams;
import com.zjicm.cooperation.service.CooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一账号接口
 * <p>
 * Created by yujing on 2017/3/8.
 */
@Controller
@RequestMapping(value = "/auth/i/account")
public class AuthAccountApi extends RootController {
    @Autowired
    private CooperationService cooperationService;

    /**
     * 统一帐号登录
     *
     * @param request
     * @param response
     * @param loginParam
     * @param results
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder login(HttpServletRequest request,
                                HttpServletResponse response,
                                @Valid @ModelAttribute LoginParam loginParam,
                                BindingResult results
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        User user = userDao.getByNumPwd(loginParam.getAccount(), loginParam.getPassword());

        if (user == null) return jsonDataHolder.putToError(404, "账号和密码不匹配");

        UserSession userSession = doLogin(request, response, user.getId());
        if (userSession == null) return jsonDataHolder.putToError(404, "该账号状态异常");

        Map<String, String> map = new HashMap<>();
        map.put("url", "http://139.129.37.224:8080/practice/home");
        return jsonDataHolder.addToItems(map);
    }

    /**
     * 注册企业账号
     *
     * @param request
     * @param response
     * @param params
     * @param results
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder register(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @Valid @ModelAttribute CompanyRegisterParams params,
                                   BindingResult results
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;


        Company company = cooperationService.createCampay(params);
        if (company == null) return jsonDataHolder.putToError(404, "该企业账号已存在");

        return jsonDataHolder.simpleMsg(company.getId(), MsgType.add);
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
