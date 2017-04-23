package com.zjicm.api.user.student;

import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.common.web.RootController;
import com.zjicm.student.beans.StudentPatchParams;
import com.zjicm.student.domain.Student;
import com.zjicm.student.service.StudentService;
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

/**
 * 学生信息个人接口
 * <p>
 * Created by yujing on 2017/3/30.
 */
@Controller
@RequestMapping(value = "/student/i/student")
public class StudentInfoStudentApi extends RootController {

    @Autowired
    private StudentService studentService;

    /**
     * 获取个人信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder get(HttpServletRequest request,
                              HttpServletResponse response) {

        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);

        Student student = studentService.getByNum(session.getNumber());
        if (student == null) return jsonDataHolder.error101();
        return jsonDataHolder.addToItems(student);
    }

    /**
     * 单字段更新
     *
     * @param request
     * @param respons
     * @param params
     * @param results
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ResponseBody
    public JsonDataHolder patch(HttpServletRequest request,
                                HttpServletResponse respons,
                                @Valid @ModelAttribute StudentPatchParams params,
                                BindingResult results
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        UserSession session = getUserSession(request);
        Student student = studentService.getByNum(session.getNumber());

        if (student == null) return jsonDataHolder.error101();

        studentService.patchStudent(student, params);

        return jsonDataHolder.simpleMsg(student.getId(), MsgType.update);

    }
}
