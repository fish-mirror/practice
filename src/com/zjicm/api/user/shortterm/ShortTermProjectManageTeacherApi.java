package com.zjicm.api.user.shortterm;

import com.zjicm.auth.enums.AuthEnums;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.shortterm.beans.ProjectParams;
import com.zjicm.shortterm.beans.ProjectPatchParams;
import com.zjicm.shortterm.domain.ShortTermProject;
import com.zjicm.shortterm.enums.ShortTermEnums;
import com.zjicm.shortterm.service.ShortTermInfoService;
import com.zjicm.web.teacher.TeacherBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 短学期项目教师接口
 * <p>
 * Created by yujing on 2017/5/11.
 */
@Controller
@RequestMapping("/teacher/i/shortterm")
public class ShortTermProjectManageTeacherApi extends TeacherBaseController {
    @Autowired
    ShortTermInfoService shortTermService;

    /**
     * 创建短学期项目
     *
     * @param request
     * @param params
     * @param results
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder create(HttpServletRequest request,
                                 @Valid @ModelAttribute ProjectParams params,
                                 BindingResult results
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        UserSession session = getUserSession(request);


        int id = shortTermService.createProject(params, session);

        return jsonDataHolder.simpleMsg(id, MsgType.add);
    }

    /**
     * 单字段修改
     *
     * @param request
     * @param params
     * @param results
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ResponseBody
    public JsonDataHolder patch(HttpServletRequest request,
                                @Valid @ModelAttribute ProjectPatchParams params,
                                BindingResult results
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        // 判断是否是本学院的项目
        UserSession session = getUserSession(request);
        ShortTermProject project = shortTermService.getProject(params.getId());
        if (project.getInstitute() != session.getInstitute()) return jsonDataHolder.error403();

        if (project.getStatus() == ShortTermEnums.ProjectStatus.can_selected.getValue()) {
            return jsonDataHolder.putToError(405, "选课状态下的项目不可修改，请关闭后重试");
        }

        shortTermService.updateProject(params, project);
        return jsonDataHolder.simpleMsg(params.getId(), MsgType.update);
    }


    /**
     * 更新项目状态
     *
     * @param request
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder updateStatus(HttpServletRequest request,
                                       @RequestParam(value = "id", defaultValue = "0", required = false) int id,
                                       @RequestParam(value = "status", defaultValue = "0", required = false) int status

    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);

        // 检查状态是否有效
        ShortTermEnums.ProjectStatus projectStatus = ShortTermEnums.ProjectStatus.is(status);
        if (projectStatus == null) return jsonDataHolder.error400();

        if (shortTermService.updateProjectStatus(id, status, session.getInstitute(), session.getUserId()) > 0) {
            return jsonDataHolder.simpleMsg(id, MsgType.update);
        } else {
            return jsonDataHolder.error101();
        }

    }

    @Override
    public int permissionToCheck() {
        return AuthEnums.short_term_project_manage.getValue();
    }
}
