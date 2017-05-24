package com.zjicm.web;

import com.zjicm.common.web.RootController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 主页
 * <p>
 * Created by yujing on 2017/4/13.
 */
@Controller
@RequestMapping(value = "/")
public class IndexCotroller extends RootController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "account.login";
    }

}
