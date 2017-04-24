package com.zjicm.api.user.cooperation;

import com.zjicm.auth.enums.AuthEnums;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.company.domain.Company;
import com.zjicm.company.service.CompanyService;
import com.zjicm.cooperation.beans.CompanyRegisterParams;
import com.zjicm.cooperation.beans.CooperationMangeOut;
import com.zjicm.cooperation.domain.Cooperation;
import com.zjicm.cooperation.enums.CooperationStatus;
import com.zjicm.cooperation.service.CooperationService;
import com.zjicm.web.teacher.TeacherBaseController;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 合作管理接口
 * <p>
 * Created by yujing on 2017/4/23.
 */
@Controller
@RequestMapping("/teacher/i/cooperation")
public class CooperationTeacherApi extends TeacherBaseController {
    @Autowired
    private CooperationService cooperationService;
    @Autowired
    private CompanyService companyService;

    /**
     * 合作企业的分页列表
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
                               @RequestParam(value = "items_per_page", defaultValue = "10", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        CooperationStatus cooperationStatus = CooperationStatus.is(status);
        UserSession session = getUserSession(request);

        PageResult<Cooperation> result = cooperationService.page(session.getInstitute(), cooperationStatus, page, size);
        if (result == null || CollectionUtils.isEmpty(result.getResult())) return jsonDataHolder.error101();

        List<CooperationMangeOut> list = new ArrayList<>();
        result.getResult().forEach(cooperation -> list.add(new CooperationMangeOut(cooperation)));
        jsonDataHolder.putAttrToJsonDataHolder(result);
        return jsonDataHolder.addListToItems(list);
    }

    /**
     * 创建合作意向
     *
     * @param request
     * @param response
     * @param companyId
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder create(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(value = "company_id", defaultValue = "0", required = false) int companyId

    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (companyId < 0) return jsonDataHolder.error400();

        UserSession session = getUserSession(request);
        Company company = companyService.get(companyId);
        if (company == null) return jsonDataHolder.error101();

        int coopId = cooperationService.createCooperation(session.getInstitute(), company, session);
        return jsonDataHolder.simpleMsg(coopId, MsgType.add);
    }

    /**
     * 创建企业账号，建立合作
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/company", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder createCompany(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @Valid @ModelAttribute CompanyRegisterParams params,
                                        BindingResult results

    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        UserSession session = getUserSession(request);
        Company company = cooperationService.createCampay(params);
        if (company == null) return jsonDataHolder.putToError(404, "该企业账号已存在");

        int coopId = cooperationService.createCooperation(session.getInstitute(), company, session);
        return jsonDataHolder.simpleMsg(coopId, MsgType.add);
    }

    /**
     * 更新合作状态
     *
     * @param request
     * @param response
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ResponseBody
    public JsonDataHolder patch(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam(value = "id", defaultValue = "0", required = false) int id,
                                @RequestParam(value = "status", defaultValue = "-1", required = false) int status

    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        CooperationStatus cooperationStatus = CooperationStatus.is(status);

        if (id < 0 || cooperationStatus == null) return jsonDataHolder.error400();

        Cooperation cooperation = cooperationService.get(id);
        if (cooperation == null) return jsonDataHolder.error101();

        UserSession session = getUserSession(request);
        if (cooperation.getInstitute() != session.getInstitute()) return jsonDataHolder.error403();

        cooperation.setStatus(status);
        cooperationService.save(cooperation);
        return jsonDataHolder.simpleMsg(id, MsgType.update);
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

        Cooperation cooperation = cooperationService.get(id);
        if (cooperation == null) return jsonDataHolder.error101();

        UserSession session = getUserSession(request);
        if (session.getInstitute() != cooperation.getInstitute()) return jsonDataHolder.error403();

        cooperationService.delete(cooperation);
        return jsonDataHolder.simpleMsg(id, MsgType.delete);
    }

    @Override
    public int permissionToCheck() {
        return AuthEnums.cooperation_manage.getValue();
    }
}
