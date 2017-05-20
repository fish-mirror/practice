package com.zjicm.api.user.practice;

import com.zjicm.attachment.domain.Attachment;
import com.zjicm.attachment.enums.AttEnums;
import com.zjicm.attachment.service.AttachmentService;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.practice.beans.PracticeParams;
import com.zjicm.practice.beans.PracticePatchParams;
import com.zjicm.practice.domain.PracticeInfo;
import com.zjicm.practice.service.PracticeGenerateService;
import com.zjicm.practice.service.PracticeInfoService;
import com.zjicm.web.student.StudentBaseController;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 实习信息学生接口
 * <p>
 * Created by yujing on 2017/5/20.
 */
@Controller
@RequestMapping("/student/i/practice")
public class PracticeStudentApi extends StudentBaseController {
    @Autowired
    private PracticeInfoService practiceInfoService;
    @Autowired
    private PracticeGenerateService practiceGenerateService;
    @Autowired
    private AttachmentService attachmentService;

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
        PracticeInfo practiceInfo = practiceInfoService.getPracticeInfo(id, session.getNumber());
        if (practiceInfo == null) return jsonDataHolder.error101();

        return jsonDataHolder.addToItems(practiceInfo);
    }


    /**
     * 实习数据列表
     *
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder list(HttpServletRequest request,
                               @RequestParam(value = "page_index", defaultValue = "1", required = false) int page,
                               @RequestParam(value = "items_per_page", defaultValue = "5", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);

        PageResult<PracticeInfo> pr = practiceInfoService.pagePracticeInfoByStudent(session.getNumber(),
                                                                                    page,
                                                                                    size);

        if (pr == null || CollectionUtils.isEmpty(pr.getResult())) return jsonDataHolder.error101();

        jsonDataHolder.putAttrToJsonDataHolder(pr);
        return jsonDataHolder.addListToItems(pr.getResult());
    }


    /**
     * 创建实习信息，提交实习申请
     *
     * @param request
     * @param params
     * @param results
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder create(HttpServletRequest request,
                                 @Valid @ModelAttribute PracticeParams params,
                                 BindingResult results
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        UserSession session = getUserSession(request);
        // TODO 创建之前默认把原来的实习信息的状态设置为取消？

        int id = practiceGenerateService.createPracticeInfo(params, session);

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
                                @Valid @ModelAttribute PracticePatchParams params,
                                BindingResult results
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        UserSession session = getUserSession(request);
        PracticeInfo practiceInfo = practiceInfoService.getPracticeInfo(params.getId(), session.getNumber());
        if (practiceInfo == null) return jsonDataHolder.error101();
        if (!practiceGenerateService.canUpdateInfo(practiceInfo.getStatus())) {
            return jsonDataHolder.putToError(406, "实习信息在当前状态下不允许被修改");
        }

        practiceGenerateService.updatePracticeInfo(params, practiceInfo, session.getUserId());
        return jsonDataHolder.simpleMsg(params.getId(), MsgType.update);
    }


    /**
     * 关联报告附件
     *
     * @param request
     * @param id
     * @param attId
     * @return
     */
    @RequestMapping(value = "/link", method = RequestMethod.PATCH)
    @ResponseBody
    public JsonDataHolder linkAtt(HttpServletRequest request,
                                  @RequestParam(value = "id", defaultValue = "", required = false) int id,
                                  @RequestParam(value = "att_id", defaultValue = "", required = false) int attId

    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);

        PracticeInfo practiceInfo = practiceInfoService.getPracticeInfo(id, session.getNumber());
        if (practiceInfo == null) return jsonDataHolder.error101();

        Attachment attachment = attachmentService.getById(attId, session.getUserId());
        if (attachment == null) return jsonDataHolder.putToError(101, "该附件不存在或不是所有者");
        if (attachment.getObjectType() != AttEnums.Type.practice_report.getValue()) return jsonDataHolder.error403();

        practiceGenerateService.linkReport(practiceInfo, attId);

        return jsonDataHolder.simpleMsg(id, MsgType.update);
    }
}
