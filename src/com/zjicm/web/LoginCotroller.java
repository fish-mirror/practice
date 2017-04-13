package com.zjicm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yujing on 2017/4/13.
 */
@Controller
public class LoginCotroller {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "account.login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "account.register";
    }
}
