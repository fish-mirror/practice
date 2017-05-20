package com.zjicm.api.user.practice;

import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.practice.domain.PracticeComment;
import com.zjicm.practice.domain.PracticeInfo;
import com.zjicm.practice.enums.PracticeStatus;
import com.zjicm.practice.service.PracticeInfoService;
import com.zjicm.practice.service.PracticeManageService;
import com.zjicm.web.company.CompanyBaseController;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 实习信息企业接口
 * <p>
 * Created by yujing on 2017/5/20.
 */
@Controller
@RequestMapping("/company/i/practice")
public class PracticeCompanyApi extends CompanyBaseController {
    @Autowired
    private PracticeInfoService practiceInfoService;
    @Autowired
    private PracticeManageService practiceManageService;

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
        PracticeInfo practiceInfo = practiceInfoService.getPracticeInfoByCompany(id, session.getNumber());
        if (practiceInfo == null) return jsonDataHolder.error101();

        return jsonDataHolder.addToItems(practiceInfo);
    }


    /**
     * 实习生列表
     *
     * @param request
     * @param status
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

            switch (practiceStatus) {
                case rejected:
                case unreview:
                case cancel:
                    return jsonDataHolder.putToError(400, "状态值无效");
                default:
                    break;
            }
        }

        PageResult<PracticeInfo> pr = practiceInfoService.pagePracticeInfoByCompany(session.getNumber(),
                                                                                    practiceStatus,
                                                                                    page,
                                                                                    size);

        if (pr == null || CollectionUtils.isEmpty(pr.getResult())) return jsonDataHolder.error101();

        jsonDataHolder.putAttrToJsonDataHolder(pr);
        return jsonDataHolder.addListToItems(pr.getResult());
    }

    /**
     * 评分
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/score", method = RequestMethod.PATCH)
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

        PracticeInfo practiceInfo = practiceInfoService.getPracticeInfoByCompany(id, session.getNumber());
        if (practiceInfo == null) return jsonDataHolder.error101();

        if (practiceInfo.getStatus() != PracticeStatus.can_score.getValue()) {
            return jsonDataHolder.putToError(406, "该实习状态不能进行评分");
        }

        practiceManageService.addComment(practiceInfo, session.getUserId(), score, comment);
        return jsonDataHolder.simpleMsg(id, MsgType.update);
    }
}
