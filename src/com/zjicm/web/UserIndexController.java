package com.zjicm.web;

import com.zjicm.common.web.RootController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yujing on 2017/5/13.
 */
@Controller
public class UserIndexController extends RootController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String index() {
        return "practice.index";
    }
}
