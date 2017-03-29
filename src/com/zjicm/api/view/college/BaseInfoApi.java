package com.zjicm.api.view.college;

import com.zjicm.common.lang.json.JsonDataHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yujing on 2017/3/30.
 */
@Controller
@RequestMapping(value = "/view/i/college")
public class BaseInfoApi {
    @RequestMapping(value = "/class/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder listClass(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(value = "institute", defaultValue = "", required = false) String institute
    ) {

        return null;
    }

    @RequestMapping(value = "/major/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder listMajor(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(value = "institute", defaultValue = "", required = false) String institute
    ) {

        return null;
    }
}
