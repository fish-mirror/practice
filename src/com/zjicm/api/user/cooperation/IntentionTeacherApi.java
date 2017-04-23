package com.zjicm.api.user.cooperation;

import com.zjicm.auth.enums.AuthEnums;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.cooperation.beans.IntentionParams;
import com.zjicm.cooperation.beans.IntentionPatchParams;
import com.zjicm.cooperation.domain.Intention;
import com.zjicm.cooperation.service.IntentionService;
import com.zjicm.web.teacher.TeacherBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 合作意向教师管理接口
 * <p>
 * Created by yujing on 2017/4/23.
 */
@Controller
@RequestMapping("/teacher/i/intention")
public class IntentionTeacherApi extends TeacherBaseController {
    @Autowired
    IntentionService intentionService;

    /**
     * 创建合作意向
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder create(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @Valid @ModelAttribute IntentionParams params,
                                 BindingResult results

    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        UserSession session = getUserSession(request);
        int id = intentionService.createIntention(params, session);
        if (id > 0) return jsonDataHolder.simpleMsg(id, MsgType.add);
        return jsonDataHolder.putToError(404, "创建失败");
    }

    /**
     * 修改合作意向
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ResponseBody
    public JsonDataHolder patch(HttpServletRequest request,
                                HttpServletResponse response,
                                @Valid @ModelAttribute IntentionPatchParams params,
                                BindingResult results

    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        Intention intention = intentionService.get(params.getId());
        if (intention == null) return jsonDataHolder.error101();

        UserSession session = getUserSession(request);
        if (session.getInstitute() != intention.getInstitute()) return jsonDataHolder.error403();
        intentionService.modifyIntention(intention, params, session);


        return jsonDataHolder.simpleMsg(params.getId(), MsgType.update);
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

        Intention intention = intentionService.get(id);
        if (intention == null) return jsonDataHolder.error101();

        UserSession session = getUserSession(request);
        if (session.getInstitute() != intention.getInstitute()) return jsonDataHolder.error403();

        intentionService.delete(intention);
        return jsonDataHolder.simpleMsg(id, MsgType.delete);
    }

    @Override
    public int permissionToCheck() {
        return AuthEnums.intention_manage.getValue();
    }
}
