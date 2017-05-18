package com.zjicm.api.user.shortterm;

import com.zjicm.auth.enums.AuthEnums;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.shortterm.domain.ShortTermComment;
import com.zjicm.shortterm.domain.ShortTermProject;
import com.zjicm.shortterm.domain.ShortTermReport;
import com.zjicm.shortterm.enums.ShortTermEnums;
import com.zjicm.shortterm.service.ShortTermInfoService;
import com.zjicm.shortterm.service.ShortTermSelectService;
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
 * 短学期评分
 *
 * Created by yujing on 2017/5/19.
 */
@Controller
@RequestMapping("/teacher/i/shortterm/report")
public class ReportManageTeacherApi extends TeacherBaseController {

    @Autowired
    private ShortTermInfoService shortTermInfoService;



    /**
     * 评分
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/score", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder score(HttpServletRequest request,
                                @RequestParam(value = "id", defaultValue = "0", required = false) int id,
                                @RequestParam(value = "score", defaultValue = "0", required = false) int score,
                                @RequestParam(value = "comment", defaultValue = "", required = false) String comment


    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (id <= 0) return jsonDataHolder.error400();

        UserSession session = getUserSession(request);
        ShortTermComment shortTermComment = shortTermInfoService.getComment(id, session.getUserId());
        if (shortTermComment != null) return jsonDataHolder.putToError(405, "已评过分请勿重复评分");

        ShortTermReport report = shortTermInfoService.getReport(id);
        if (report == null || report.getProject() == null) return jsonDataHolder.error101();

        if (session.getInstitute() != report.getProject().getInstitute()) {
            return jsonDataHolder.error403();
        }

        if (report.getProject().getStatus() != ShortTermEnums.ProjectStatus.can_grade.getValue()) {
            return jsonDataHolder.putToError(405, "不是评分时间不能进行评分");
        }

        shortTermInfoService.addComment(report, session.getUserId(), score, comment);


        return jsonDataHolder.simpleMsg(id, MsgType.update);
    }

    @Override
    public int permissionToCheck() {
        return AuthEnums.short_term_manage.getValue();
    }
}
