package com.zjicm.api.user.resume;

import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.resume.beans.ResumeOut;
import com.zjicm.resume.beans.ResumeParams;
import com.zjicm.resume.domain.Resume;
import com.zjicm.resume.service.ResumeService;
import com.zjicm.student.domain.Student;
import com.zjicm.web.student.StudentBaseController;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 简历个人操作接口
 * <p>
 * Created by yujing on 2017/4/22.
 */
@Controller
@RequestMapping("/user/i/resume")
public class ResumeUserApi extends StudentBaseController {
    @Autowired
    private ResumeService resumeService;


    /**
     * 获取学生的简历列表
     *
     * @param request
     * @param response
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder list(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(value = "page_index", defaultValue = "1", required = false) int page,
                               @RequestParam(value = "items_per_page", defaultValue = "10", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);
        PageResult<Resume> result = resumeService.page(session.getNumber(), page, size);
        if (result == null || CollectionUtils.isEmpty(result.getResult())) return jsonDataHolder.error101();

        List<ResumeOut> list = new ArrayList<>();
        result.getResult().forEach(resume -> {
            Student student = studentService.getByNum(resume.getStudentNumber());
            if (student == null) return;
            list.add(new ResumeOut(resume, student));
        });
        jsonDataHolder.addListToItems(list);
        jsonDataHolder.putAttrToJsonDataHolder(result);
        return jsonDataHolder;
    }

    /**
     * 创建简历信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder create(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @Valid @ModelAttribute ResumeParams params,
                                 BindingResult results

    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        UserSession session = getUserSession(request);
        int id = resumeService.createResume(params, session);
        if (id > 0) return jsonDataHolder.simpleMsg(id, MsgType.add);
        return jsonDataHolder.putToError(404, "创建失败");
    }

    /**
     * 修改简历信息
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ResponseBody
    public JsonDataHolder patch(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam(value = "id", defaultValue = "0", required = false) int id,
                                @Valid @ModelAttribute ResumeParams params,
                                BindingResult results

    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (id <= 0) return jsonDataHolder.error400();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        Resume resume = resumeService.get(id);
        if (resume == null) return jsonDataHolder.error101();

        UserSession session = getUserSession(request);
        resumeService.modifyResume(resume, params, session);


        return jsonDataHolder.simpleMsg(id, MsgType.update);
    }

    /**
     * 删除
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonDataHolder delete(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(value = "id", defaultValue = "0", required = false) int id

                                 ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (id <= 0) return jsonDataHolder.error400();

        Resume resume = resumeService.get(id);
        if (resume == null) return jsonDataHolder.error101();

        UserSession session = getUserSession(request);
        if (!resume.getStudentNumber().equals(session.getNumber())) return jsonDataHolder.error403();

        resumeService.delete(resume);
        return jsonDataHolder.simpleMsg(id, MsgType.delete);
    }
}
