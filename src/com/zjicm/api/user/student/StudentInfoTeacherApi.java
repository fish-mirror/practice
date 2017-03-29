package com.zjicm.api.user.student;

import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.web.RootController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 学生信息接口(教职工管理接口)
 * Created by yujing on 2017/3/30.
 */
@Controller
@RequestMapping(value = "/teacher/i/student")
public class StudentInfoTeacherApi extends RootController {

    /**
     * 获取学生信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder get(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam(value = "number", defaultValue = "", required = false) String number
    ) {

        return null;
    }

    /**
     * 获取所在学院的学生列表信息（仅教职工）
     *
     * @param request
     * @param response
     * @param status
     * @param classname
     * @param isGraduate
     * @param number
     * @param name
     * @param page_index
     * @param items_per_page
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder list(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(value = "status", defaultValue = "0", required = false) int status,
                               @RequestParam(value = "classname", defaultValue = "", required = false) String classname,
                               @RequestParam(value = "is_graduate", defaultValue = "null", required = false) Boolean isGraduate,
                               @RequestParam(value = "number", defaultValue = "", required = false) String number,
                               @RequestParam(value = "name", defaultValue = "", required = false) String name,
                               @RequestParam(value = "page_index", defaultValue = "1", required = false) int page_index,
                               @RequestParam(value = "items_per_page", defaultValue = "10", required = false) int items_per_page
    ) {

        return null;
    }

    /**
     * 获取本学院的学生实习状态统计分布（仅教职工）
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder getStatusDistribute(HttpServletRequest request,
                                              HttpServletResponse response) {

        return null;
    }


}
