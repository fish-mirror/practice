package com.zjicm.api.user.student;

import com.zjicm.auth.enums.Role;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.common.web.RootController;
import com.zjicm.student.domain.Student;
import com.zjicm.student.service.StudentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private StudentService studentService;

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
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (StringUtils.isBlank(number)) return jsonDataHolder.error400();

        UserSession session = getUserSession(request);

        Student student = studentService.getByNum(number);
        if (student == null) return jsonDataHolder.error101();

        if (Role.teacher.getValue() != session.getRoleId()) return jsonDataHolder.error403();
        if (session.getInstitute() != student.getInstitute()) return jsonDataHolder.error403();

        return jsonDataHolder.addToItems(student);
    }

    /**
     * @param request
     * @param response
     * @param status         实习状态
     * @param grade          年级
     * @param major          专业代号
     * @param classIndex     班级号
     * @param isGraduating   是否筛选毕业班
     * @param name           姓名
     * @param page_index
     * @param items_per_page
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder list(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(value = "status", defaultValue = "0", required = false) int status,
                               @RequestParam(value = "grade", defaultValue = "", required = false) int grade,
                               @RequestParam(value = "major", defaultValue = "", required = false) String major,
                               @RequestParam(value = "class_index", defaultValue = "0", required = false) int classIndex,
                               @RequestParam(value = "is_graduating", defaultValue = "false", required = false) Boolean isGraduating,
                               @RequestParam(value = "name", defaultValue = "", required = false) String name,
                               @RequestParam(value = "page_index", defaultValue = "1", required = false) int page_index,
                               @RequestParam(value = "items_per_page", defaultValue = "10", required = false) int items_per_page
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();

        UserSession session = getUserSession(request);
        PageResult result = studentService.pageStudent(session.getInstitute(),
                                                       isGraduating,
                                                       grade,
                                                       major,
                                                       classIndex,
                                                       status,
                                                       name,
                                                       page_index,
                                                       items_per_page);


        jsonDataHolder.putAttrToJsonDataHolder(result);
        return jsonDataHolder.addListToItems(result.getResult());
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
