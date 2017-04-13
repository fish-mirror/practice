package com.zjicm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yujing on 2017/4/13.
 */
@Controller
@RequestMapping(value = "/")
public class IndexCotroller {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "layout.index";
    }
}
