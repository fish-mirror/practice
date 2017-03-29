package com.zjicm.api.user.student;

import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.web.RootController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 学生信息个人接口
 * Created by yujing on 2017/3/30.
 */
@Controller
@RequestMapping(value = "/user/i/student")
public class StudentInfoUserApi extends RootController {

    /**
     * 获取个人信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder get(HttpServletRequest request,
                              HttpServletResponse response) {

        return null;
    }


}
