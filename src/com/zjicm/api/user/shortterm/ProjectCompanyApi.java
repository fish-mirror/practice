package com.zjicm.api.user.shortterm;

import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.shortterm.beans.ProjectParams;
import com.zjicm.shortterm.beans.ProjectPatchParams;
import com.zjicm.shortterm.domain.ShortTermProject;
import com.zjicm.shortterm.enums.ShortTermEnums;
import com.zjicm.shortterm.service.ShortTermInfoService;
import com.zjicm.web.company.CompanyBaseController;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by yujing on 2017/5/14.
 */
@Controller
@RequestMapping("/company/i/shortterm")
public class ProjectCompanyApi extends CompanyBaseController {
    @Autowired
    private ShortTermInfoService shortTermInfoService;
    /**
     * 单数据获取
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/project", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder get(HttpServletRequest request,
                              @RequestParam(value = "id", defaultValue = "0", required = false) int id
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (id <= 0) return jsonDataHolder.error400();
        UserSession session = getUserSession(request);

        ShortTermProject shortTermProject = shortTermInfoService.getProject(id, session.getNumber());
        if (shortTermProject == null) return jsonDataHolder.error101();

        return jsonDataHolder.addToItems(shortTermProject);
    }

    /**
     * 短学期项目列表
     *
     * @param request
     * @param term
     * @param status
     * @param fullStatus
     * @param gradeNeed
     * @param majorNeed
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/project/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder list(HttpServletRequest request,
                               @RequestParam(value = "name", defaultValue = "", required = false) String name,
                               @RequestParam(value = "term", defaultValue = "", required = false) String term,
                               @RequestParam(value = "status", defaultValue = "-1", required = false) int status,
                               @RequestParam(value = "full_status", defaultValue = "0", required = false) int fullStatus,
                               @RequestParam(value = "grade_need", defaultValue = "0", required = false) int gradeNeed,
                               @RequestParam(value = "major_need", defaultValue = "", required = false) String majorNeed,
                               @RequestParam(value = "page_index", defaultValue = "1", required = false) int page,
                               @RequestParam(value = "items_per_page", defaultValue = "10", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);
        ShortTermEnums.ProjectStatus projectStatus = ShortTermEnums.ProjectStatus.is(status);
        ShortTermEnums.ProjectFull projectFull = ShortTermEnums.ProjectFull.is(fullStatus);

        PageResult<ShortTermProject> pr = shortTermInfoService.pageProjects(0,
                                                                            session.getNumber(),
                                                                            name,
                                                                            term,
                                                                            projectStatus,
                                                                            projectFull,
                                                                            gradeNeed,
                                                                            majorNeed,
                                                                            page,
                                                                            size);

        if (pr == null || CollectionUtils.isEmpty(pr.getResult())) return jsonDataHolder.error101();

        jsonDataHolder.putAttrToJsonDataHolder(pr);
        return jsonDataHolder.addListToItems(pr.getResult());
    }

    /**
     * 创建短学期项目
     *
     * @param request
     * @param params
     * @param results
     * @return
     */
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder create(HttpServletRequest request,
                                 @Valid @ModelAttribute ProjectParams params,
                                 BindingResult results
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        UserSession session = getUserSession(request);


        int id = shortTermInfoService.createProject(params, session);

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
    @RequestMapping(value = "/project", method = RequestMethod.PATCH)
    @ResponseBody
    public JsonDataHolder patch(HttpServletRequest request,
                                @Valid @ModelAttribute ProjectPatchParams params,
                                BindingResult results
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        UserSession session = getUserSession(request);
        ShortTermProject project = shortTermInfoService.getProject(params.getId(), session.getNumber());
        if (project == null) return jsonDataHolder.error403();

        if (project.getStatus() == ShortTermEnums.ProjectStatus.can_selected.getValue()) {
            return jsonDataHolder.putToError(405, "选课状态下的项目不可修改，请关闭后重试");
        }

        shortTermInfoService.updateProject(params, project);
        return jsonDataHolder.simpleMsg(params.getId(), MsgType.update);
    }
}
