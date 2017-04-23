package com.zjicm.api.view.resume;

import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.util.StringUtil;
import com.zjicm.common.web.RootController;
import com.zjicm.resume.beans.ResumeOut;
import com.zjicm.resume.domain.Resume;
import com.zjicm.resume.service.ResumeService;
import com.zjicm.student.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 简历信息接口
 * <p>
 * Created by yujing on 2017/4/22.
 */
@Controller
@RequestMapping("/view/i/resume")
public class ResumeViewApi extends RootController {
    @Autowired
    private ResumeService resumeService;

    /**
     * 单简历
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder get(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam(value = "id", defaultValue = "0", required = false) int id
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (id < 0) return jsonDataHolder.error400();

        Resume resume = resumeService.get(id);
        if (resume == null || StringUtil.isBlank(resume.getStudentNumber())) return jsonDataHolder.error101();
        Student student = studentService.getByNum(resume.getStudentNumber());
        if (student == null) return jsonDataHolder.error101();


        return jsonDataHolder.addToItems(new ResumeOut(resume, student));
    }


    @Override
    public boolean checkLogin() {
        return true;
    }
}
