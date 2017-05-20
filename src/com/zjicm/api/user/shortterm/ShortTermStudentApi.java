package com.zjicm.api.user.shortterm;

import com.zjicm.attachment.domain.Attachment;
import com.zjicm.attachment.enums.AttEnums;
import com.zjicm.attachment.service.AttachmentService;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.shortterm.domain.ShortTermProject;
import com.zjicm.shortterm.domain.ShortTermReport;
import com.zjicm.shortterm.enums.ShortTermEnums;
import com.zjicm.shortterm.service.ShortTermInfoService;
import com.zjicm.shortterm.service.ShortTermSelectService;
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
public class ShortTermStudentApi extends StudentBaseController {
    @Autowired
    private ShortTermInfoService shortTermInfoService;
    @Autowired
    private ShortTermSelectService shortTermSelectService;
    @Autowired
    private AttachmentService attachmentService;


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

        ShortTermProject shortTermProject = shortTermInfoService.getProject(id);
        if (shortTermProject == null) return jsonDataHolder.error101();

        UserSession session = getUserSession(request);
        if (shortTermProject.getInstitute() != session.getInstitute()) return jsonDataHolder.error403();

        return jsonDataHolder.addToItems(shortTermProject);
    }


    /**
     * 短学期项目选课列表（仅提供当前学期，状态为可选课的短学期项目）
     *
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/project/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder list(HttpServletRequest request,
                               @RequestParam(value = "page_index", defaultValue = "1", required = false) int page,
                               @RequestParam(value = "items_per_page", defaultValue = "10", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);

        PageResult<ShortTermProject> pr = shortTermSelectService.pageShortTermProject(session.getInstitute(),
                                                                                      page,
                                                                                      size);

        if (pr == null || CollectionUtils.isEmpty(pr.getResult())) return jsonDataHolder.error101();

        jsonDataHolder.putAttrToJsonDataHolder(pr);
        return jsonDataHolder.addListToItems(pr.getResult());
    }

    /**
     * 选课/取消选课
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder select(HttpServletRequest request,
                                 @RequestParam(value = "id", defaultValue = "0", required = false) int id,
                                 @RequestParam(value = "select", defaultValue = "true", required = false) boolean select

    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (id <= 0) return jsonDataHolder.error400();
        UserSession session = getUserSession(request);

        ShortTermProject project = shortTermSelectService.getProjectFromCanSelect(id, session.getInstitute());
        if (project == null) {
            project = shortTermInfoService.getProject(id,
                                                      ShortTermEnums.ProjectStatus.can_selected.getValue(),
                                                      session.getInstitute());
            if (project == null) return jsonDataHolder.error101();

        }


        if (select) {
            String result = shortTermSelectService.selectProject(project, session);
            if (StringUtils.isNotBlank(result)) return jsonDataHolder.putToError(406, result);

            return jsonDataHolder.simpleMsg(id, MsgType.add);
        } else {
            boolean result = shortTermSelectService.cancalProject(project, session);
            if (!result) return jsonDataHolder.putToError(406, "状态异常");

            return jsonDataHolder.simpleMsg(id, MsgType.delete);
        }
    }

    /**
     * 当前已选短学期项目
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/current/project", method = RequestMethod.GET)
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
    @RequestMapping(value = "/report/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder reports(HttpServletRequest request,
                                  @RequestParam(value = "page_index", defaultValue = "1", required = false) int page,
                                  @RequestParam(value = "items_per_page", defaultValue = "10", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);

        PageResult<ShortTermReport> pr = shortTermInfoService.pageReport(session.getNumber(), page, size);

        if (pr == null || CollectionUtils.isEmpty(pr.getResult())) return jsonDataHolder.error101();

        jsonDataHolder.putAttrToJsonDataHolder(pr);
        return jsonDataHolder.addListToItems(pr.getResult());
    }

    /**
     * 关联报告附件
     *
     * @param request
     * @param id
     * @param attId
     * @return
     */
    @RequestMapping(value = "/report", method = RequestMethod.PATCH)
    @ResponseBody
    public JsonDataHolder linkAtt(HttpServletRequest request,
                                  @RequestParam(value = "id", defaultValue = "", required = false) int id,
                                  @RequestParam(value = "att_id", defaultValue = "", required = false) int attId

    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);

        ShortTermReport report = shortTermInfoService.getReport(id);
        if (report == null || report.getStudent() == null ||
            !session.getNumber().equals(report.getStudent().getNumber())) {
            return jsonDataHolder.putToError(101, "该报告不存在或不是所有者");
        }

        Attachment attachment = attachmentService.getById(attId, session.getUserId());
        if (attachment == null) return jsonDataHolder.putToError(101, "该附件不存在或不是所有者");
        if (attachment.getObjectType() != AttEnums.Type.short_term_report.getValue()) return jsonDataHolder.error403();

        report.setAttId(attId);
        shortTermInfoService.saveReport(report);

        return jsonDataHolder.simpleMsg(id, MsgType.update);
    }

}
