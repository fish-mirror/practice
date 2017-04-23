package com.zjicm.api.view.cooperation;

import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.common.web.RootController;
import com.zjicm.cooperation.beans.CooperationOut;
import com.zjicm.cooperation.domain.Cooperation;
import com.zjicm.cooperation.enums.CooperationStatus;
import com.zjicm.cooperation.service.CooperationService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 合作企业信息接口
 * <p>
 * Created by yujing on 2017/4/23.
 */
@Controller
@RequestMapping("/view/i/cooperation")
public class CooperationViewApi extends RootController {
    @Autowired
    private CooperationService cooperationService;

    /**
     * 合作企业的分页列表
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
                               @RequestParam(value = "items_per_page", defaultValue = "10", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);

        // 通用接口只返回正常状态的合作企业
        PageResult<Cooperation> result = cooperationService.page(session.getInstitute(),
                                                                 CooperationStatus.cooperating,
                                                                 page,
                                                                 size);
        if (result == null || CollectionUtils.isEmpty(result.getResult())) return jsonDataHolder.error101();

        List<CooperationOut> list = new ArrayList<>();
        result.getResult().forEach(cooperation -> list.add(new CooperationOut(cooperation)));
        jsonDataHolder.putAttrToJsonDataHolder(result);
        return jsonDataHolder.addListToItems(list);
    }
}
