package com.zjicm.api.user.practice;

import com.zjicm.auth.enums.AuthEnums;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.practice.domain.PracticeComment;
import com.zjicm.practice.domain.PracticeInfo;
import com.zjicm.practice.enums.PracticeStatus;
import com.zjicm.practice.service.PracticeInfoService;
import com.zjicm.practice.service.PracticeManageService;
import com.zjicm.web.teacher.TeacherBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 实习信息教师管理接口
 * <p>
 * Created by yujing on 2017/5/20.
 */
@Controller
@RequestMapping("/teacher/i/practice")
public class PracticeManageTeacherApi extends TeacherBaseController {
    @Autowired
    private PracticeInfoService practiceInfoService;
    @Autowired
    private PracticeManageService practiceManageService;


    /**
     * 同意实习申请
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder agree(HttpServletRequest request,
                                @RequestParam(value = "id", defaultValue = "0", required = false) int id,
                                @RequestParam(value = "agree", defaultValue= "true", required = false) boolean agree

    ){
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        PracticeInfo practiceInfo = practiceInfoService.getPracticeInfo(id);
        if (practiceInfo == null) return jsonDataHolder.error101();
        if (practiceInfo.getStatus() != PracticeStatus.unreview.getValue()) {
            return jsonDataHolder.putToError(406, "实习信息状态异常，无法同意");
        }
        UserSession session = getUserSession(request);
        if (practiceInfo.getStudent().getInstitute() != session.getInstitute()) return jsonDataHolder.error403();

        practiceManageService.agreePracticeApply(practiceInfo, agree);
        return jsonDataHolder.simpleMsg(id, MsgType.update);
    }



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
        PracticeComment practiceComment = practiceInfoService.getPracticeComment(id, session.getUserId());
        if (practiceComment != null) return jsonDataHolder.putToError(406, "已评过分请勿重复评分");

        PracticeInfo practiceInfo = practiceInfoService.getPracticeInfo(id, session.getInstitute());
        if (practiceInfo == null) return jsonDataHolder.error101();

        if (practiceInfo.getStatus() != PracticeStatus.can_score.getValue()) {
            return jsonDataHolder.putToError(406, "该实习状态不能进行评分");
        }

        practiceManageService.addComment(practiceInfo, session.getUserId(), score, comment);
        return jsonDataHolder.simpleMsg(id, MsgType.update);
    }

    @Override
    public int permissionToCheck() {
        return AuthEnums.practice_manage.getValue();
    }
}
