package com.zjicm.api.user.shortterm;

import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.shortterm.domain.ShortTermProject;
import com.zjicm.shortterm.enums.ShortTermEnums;
import com.zjicm.shortterm.service.ShortTermInfoService;
import com.zjicm.web.teacher.TeacherBaseController;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 短学期项目教师接口
 * <p>
 * Created by yujing on 2017/5/11.
 */
@Controller
@RequestMapping("/teacher/i/shortterm")
public class ShortTermProjectTeacherApi extends TeacherBaseController {
    @Autowired
    private ShortTermInfoService shortTermInfoService;

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

        ShortTermProject shortTermProject = shortTermInfoService.getProject(id);
        if (shortTermProject == null) return jsonDataHolder.error101();

        UserSession session = getUserSession(request);
        if (shortTermProject.getInstitute() != session.getInstitute()) return jsonDataHolder.error403();

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
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder list(HttpServletRequest request,
                               @RequestParam(value = "name", defaultValue = "", required = false) String name,
                               @RequestParam(value = "term", defaultValue = "", required = false) String term,
                               @RequestParam(value = "status", defaultValue = "-1", required = false) int status,
                               @RequestParam(value = "company_number", defaultValue = "", required = false) String companyNumber,
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

        PageResult<ShortTermProject> pr = shortTermInfoService.pageProjects(session.getInstitute(),
                                                                            companyNumber,
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
}
