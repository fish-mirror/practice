package com.zjicm.api.user.practice;

import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.practice.domain.PracticeInfo;
import com.zjicm.practice.enums.PracticeStatus;
import com.zjicm.practice.service.PracticeInfoService;
import com.zjicm.web.teacher.TeacherBaseController;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 实习信息教师查询接口
 * <p>
 * Created by yujing on 2017/5/20.
 */
@Controller
@RequestMapping("/teacher/i/practice")
public class PracticeTeacherApi extends TeacherBaseController {
    @Autowired
    private PracticeInfoService practiceInfoService;

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

        UserSession session = getUserSession(request);
        PracticeInfo practiceInfo = practiceInfoService.getPracticeInfo(id, session.getInstitute());
        if (practiceInfo == null) return jsonDataHolder.error101();

        return jsonDataHolder.addToItems(practiceInfo);
    }


    /**
     * 实习信息列表，
     *
     * @param request
     * @param status     status = 0 时，表示的是待审核实习申请列表
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder list(HttpServletRequest request,
                               @RequestParam(value = "status", defaultValue = "-1", required = false) int status,
                               @RequestParam(value = "page_index", defaultValue = "1", required = false) int page,
                               @RequestParam(value = "items_per_page", defaultValue = "5", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);

        PracticeStatus practiceStatus = null;
        if (status > -1) {
            practiceStatus = PracticeStatus.is(status);
            if (practiceStatus == null) return jsonDataHolder.putToError(400, "状态值无效");
        }

        PageResult<PracticeInfo> pr = practiceInfoService.pagePracticeInfoByInstitute(session.getInstitute(),
                                                                                      practiceStatus,
                                                                                      page,
                                                                                      size);

        if (pr == null || CollectionUtils.isEmpty(pr.getResult())) return jsonDataHolder.error101();

        jsonDataHolder.putAttrToJsonDataHolder(pr);
        return jsonDataHolder.addListToItems(pr.getResult());
    }
}
