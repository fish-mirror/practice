package com.zjicm.api.view.college;

import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.student.beans.ClassInfoDto;
import com.zjicm.student.beans.ClassInfoOut;
import com.zjicm.student.dao.StudentDao;
import com.zjicm.student.service.StudentService;
import com.zjicm.student.support.CollegeInfoSupport;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 学院基本信息接口
 * <p>
 * Created by yujing on 2017/3/30.
 */
@Controller
@RequestMapping(value = "/view/i/college")
public class CollegeInfoApi {
    @Autowired
    private StudentService studentService;

    /**
     * 获取学院的班级列表
     *
     * @param request
     * @param response
     * @param institute
     * @return
     */
    @RequestMapping(value = "/class/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder listClass(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestParam(value = "institute", defaultValue = "", required = false) int institute
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();

        List<ClassInfoOut> list = studentService.getClassList(institute);
        if (CollectionUtils.isEmpty(list)) return jsonDataHolder.error101();

        return jsonDataHolder.addListToItems(list);
    }

    /**
     * 获取学院的专业列表
     *
     * @param request
     * @param response
     * @param institute
     * @return
     */
    @RequestMapping(value = "/major/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder listMajor(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestParam(value = "institute", defaultValue = "", required = false) int institute
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (institute < 0) return jsonDataHolder.error400();
        List list = CollegeInfoSupport.getMajorList(institute);

        if (CollectionUtils.isEmpty(list)) return jsonDataHolder.error101();
        return jsonDataHolder.addListToItems(list);
    }
}
