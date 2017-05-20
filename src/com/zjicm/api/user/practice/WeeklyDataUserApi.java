package com.zjicm.api.user.practice;

import com.zjicm.common.beans.NameValue;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.common.web.RootController;
import com.zjicm.practice.domain.PracticeData;
import com.zjicm.practice.service.WeeklyDataService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 实习周记相关接口
 * <p>
 * Created by yujing on 2017/5/21.
 */
@Controller
@RequestMapping("user/i/practice/data")
public class WeeklyDataUserApi extends RootController {
    @Autowired
    private WeeklyDataService weeklyDataService;

    /**
     * 实习周记分页列表
     *
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder listData(HttpServletRequest request,
                                   @RequestParam(value = "page_index", defaultValue = "1", required = false) int page,
                                   @RequestParam(value = "items_per_page", defaultValue = "5", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);
        PageResult<PracticeData> pr = weeklyDataService.page(session.getInstitute(), page, size);

        if (pr == null || CollectionUtils.isEmpty(pr.getResult())) return jsonDataHolder.error101();

        jsonDataHolder.putAttrToJsonDataHolder(pr);
        return jsonDataHolder.addListToItems(pr.getResult());
    }


    /**
     * 最近 7 天的实习周记
     *
     * @param request
     * @param province 支持省份筛选
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/recent", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder recent(HttpServletRequest request,
                                 @RequestParam(value = "province", defaultValue = "", required = false) String province,
                                 @RequestParam(value = "page_index", defaultValue = "1", required = false) int page,
                                 @RequestParam(value = "items_per_page", defaultValue = "5", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);

        PageResult<PracticeData> pr = weeklyDataService.pageByRecentProvince(session.getInstitute(),
                                                                             province,
                                                                             page,
                                                                             size);
        if (pr == null || CollectionUtils.isEmpty(pr.getResult())) return jsonDataHolder.error101();

        jsonDataHolder.putAttrToJsonDataHolder(pr);
        return jsonDataHolder.addListToItems(pr.getResult());
    }

    /**
     * 最近 7 天实习地理位置分布
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/location/map", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder locationMap(HttpServletRequest request) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);

        List<NameValue<String, Integer>> list = weeklyDataService.locationMap(session.getInstitute());
        return jsonDataHolder.addListToItems(list);
    }


    @Override
    public boolean isApi() {
        return true;
    }

    @Override
    public boolean checkLogin() {
        return true;
    }
}
