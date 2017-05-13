package com.zjicm.api.user.shortterm;

import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.shortterm.domain.ShortTermProject;
import com.zjicm.shortterm.domain.ShortTermReport;
import com.zjicm.shortterm.enums.ShortTermEnums;
import com.zjicm.shortterm.service.ShortTermInfoService;
import com.zjicm.shortterm.service.ShortTermSelectService;
import com.zjicm.student.support.CollegeInfoSupport;
import com.zjicm.web.student.StudentBaseController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 短学期学生接口
 * <p>
 * Created by yujing on 2017/5/13.
 */
@Controller
@RequestMapping("/student/i/shortterm")
public class ShortTermProjectStudentApi extends StudentBaseController {
    @Autowired
    ShortTermInfoService shortTermService;
    @Autowired
    ShortTermSelectService shortTermSelectService;


    /**
     * 单数据获取
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder get(HttpServletRequest request,
                              @RequestParam(value = "id", defaultValue = "0", required = false) int id
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (id <= 0) return jsonDataHolder.error400();

        ShortTermProject shortTermProject = shortTermService.getProject(id);
        if (shortTermProject == null) return jsonDataHolder.error101();

        UserSession session = getUserSession(request);
        if (shortTermProject.getInstitute() != session.getInstitute()) return jsonDataHolder.error403();

        return jsonDataHolder.addToItems(shortTermProject);
    }


    /**
     * 短学期项目选课列表（仅提供当前学期，状态为可选课的短学期项目）
     *
     * @param request
     * @param fullStatus
     * @param gradeNeed
     * @param majorNeed
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder list(HttpServletRequest request,
                               @RequestParam(value = "name", defaultValue = "", required = false) String name,
                               @RequestParam(value = "full_status", defaultValue = "0", required = false) int fullStatus,
                               @RequestParam(value = "grade_need", defaultValue = "0", required = false) int gradeNeed,
                               @RequestParam(value = "major_need", defaultValue = "", required = false) String majorNeed,
                               @RequestParam(value = "page_index", defaultValue = "1", required = false) int page,
                               @RequestParam(value = "items_per_page", defaultValue = "10", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);
        ShortTermEnums.ProjectFull projectFull = ShortTermEnums.ProjectFull.is(fullStatus);

        PageResult<ShortTermProject> pr = shortTermService.pageProjects(session.getInstitute(),
                                                                        null,
                                                                        name,
                                                                        CollegeInfoSupport.getCurrentTerm(),
                                                                        ShortTermEnums.ProjectStatus.can_selected,
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
     * 选课
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder select(HttpServletRequest request,
                                 @RequestParam(value = "id", defaultValue = "0", required = false) int id
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (id <= 0) return jsonDataHolder.error400();
        UserSession session = getUserSession(request);

        ShortTermProject project = shortTermSelectService.getProjectFromCanSelect(id, session.getInstitute());
        if (project == null) return jsonDataHolder.error101();

        String result = shortTermSelectService.selectProject(project, session);
        if (StringUtils.isNotBlank(result)) return jsonDataHolder.putToError(405, result);

        return jsonDataHolder.simpleMsg(id, MsgType.add);
    }

    /**
     * 取消选课
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder cancel(HttpServletRequest request,
                                 @RequestParam(value = "id", defaultValue = "0", required = false) int id
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (id <= 0) return jsonDataHolder.error400();
        UserSession session = getUserSession(request);

        ShortTermProject project = shortTermSelectService.getProjectFromCanSelect(id, session.getInstitute());
        if (project == null) return jsonDataHolder.error101();

        boolean result = shortTermSelectService.cancalProject(project, session);
        if (!result) return jsonDataHolder.putToError(405, "状态异常");

        return jsonDataHolder.simpleMsg(id, MsgType.delete);
    }

    /**
     * 当前已选短学期项目
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/selected", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder selected(HttpServletRequest request) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);

        ShortTermProject project = shortTermSelectService.getCurrentSelectedProject(session.getNumber());

        if (project == null) return jsonDataHolder.error101();

        return jsonDataHolder.addToItems(project);
    }

    /**
     * 短学期报告列表
     *
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder reports(HttpServletRequest request,
                                  @RequestParam(value = "page_index", defaultValue = "1", required = false) int page,
                                  @RequestParam(value = "items_per_page", defaultValue = "10", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);

        PageResult<ShortTermReport> pr = shortTermService.pageReport(session.getNumber(), page, size);

        if (pr == null || CollectionUtils.isEmpty(pr.getResult())) return jsonDataHolder.error101();

        jsonDataHolder.putAttrToJsonDataHolder(pr);
        return jsonDataHolder.addListToItems(pr.getResult());
    }

}
