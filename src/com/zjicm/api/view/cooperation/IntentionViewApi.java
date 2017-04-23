package com.zjicm.api.view.cooperation;

import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.common.web.RootController;
import com.zjicm.cooperation.domain.Intention;
import com.zjicm.cooperation.service.IntentionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 合作意向信息
 * Created by yujing on 2017/4/23.
 */
@Controller
@RequestMapping("/view/i/intention")
public class IntentionViewApi extends RootController {
    @Autowired
    IntentionService intentionService;

    /**
     * 单合作意向查看接口
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder get(@RequestParam(value = "id", defaultValue = "0", required = false) int id
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (id < 0) return jsonDataHolder.error400();

        Intention intention = intentionService.get(id);
        if (intention == null) return jsonDataHolder.error101();

        return jsonDataHolder.addToItems(intention);
    }

    /**
     * 合作意向分页列表
     *
     * @param institute
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder list(@RequestParam(value = "institute", defaultValue = "0", required = false) int institute,
                               @RequestParam(value = "page_index", defaultValue = "1", required = false) int page,
                               @RequestParam(value = "items_per_page", defaultValue = "10", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();

        PageResult<Intention> result = intentionService.page(institute, page, size);
        if (result == null || CollectionUtils.isEmpty(result.getResult())) return jsonDataHolder.error101();

        jsonDataHolder.addListToItems(result.getResult());
        jsonDataHolder.putAttrToJsonDataHolder(result);
        return jsonDataHolder;
    }
}
