package com.zjicm.api.admin;

import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.customkeyvalue.beans.CustomKeyParams;
import com.zjicm.customkeyvalue.dao.CustomKeyValueDao;
import com.zjicm.customkeyvalue.domain.CustomKeyValue;
import com.zjicm.web.admin.AdminBaseController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

/**
 * 自定义键值对后台管理接口
 * <p>
 * Created by yujing on 2017/5/13.
 */
@Controller
@RequestMapping(value = "/admin/i/customkey")
public class CustomKeyAdminApi extends AdminBaseController {

    @Autowired
    private CustomKeyValueDao customKeyValueDao;

    /**
     * 列表
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder list(@RequestParam(value = "page_index", defaultValue = "1", required = false) int page,
                               @RequestParam(value = "items_per_page", defaultValue = "5", required = false) int size
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();

        PageResult pr = customKeyValueDao.page(page, size);
        if (pr == null || CollectionUtils.isEmpty(pr.getResult())) return jsonDataHolder.error101();


        jsonDataHolder.putAttrToJsonDataHolder(pr);
        jsonDataHolder.addListToItems(pr.getResult());

        return jsonDataHolder;
    }


    /**
     * 创建
     *
     * @param request
     * @param params
     * @param results
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder save(HttpServletRequest request,
                               @Valid @ModelAttribute CustomKeyParams params,
                               BindingResult results
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;
        UserSession session = getUserSession(request);

        if (StringUtils.isBlank(params.getKey())) return jsonDataHolder.putToError(400, "key 不为空");
        if (customKeyValueDao.ifExistByKey(params.getKey())) return jsonDataHolder.putToError(400, "key 已存在");

        CustomKeyValue customKeyValue = new CustomKeyValue();
        customKeyValue.setName(params.getName());
        customKeyValue.setKeyStr(params.getKey());
        customKeyValue.setValue(params.getValue());
        customKeyValue.setRemarks(params.getRemarks());
        customKeyValue.setModifier(session.getUserId());
        customKeyValue.setCreator(session.getUserId());
        customKeyValue.setCreateTime(new Date());

        customKeyValueDao.save(customKeyValue);
        return jsonDataHolder.simpleMsg(customKeyValue.getId(), MsgType.add);
    }

    /**
     * 单字段修改
     *
     * @param request
     * @param id
     * @param params
     * @param results
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ResponseBody
    public JsonDataHolder edit(HttpServletRequest request,
                               @RequestParam(value = "id", defaultValue = "") int id,
                               @Valid @ModelAttribute CustomKeyParams params,
                               BindingResult results
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (jsonDataHolder.checkError(results)) return jsonDataHolder;
        UserSession session = getUserSession(request);

        if (customKeyValueDao.ifExistByKey(params.getKey())) return jsonDataHolder.putToError(400, "key 已存在");

        CustomKeyValue customKeyValue = customKeyValueDao.getById(id);
        if (customKeyValue == null) return jsonDataHolder.error101();

        if (StringUtils.isNotBlank(params.getKey())) customKeyValue.setKeyStr(params.getKey());
        if (StringUtils.isNotBlank(params.getName())) customKeyValue.setName(params.getName());
        if (StringUtils.isNotEmpty(params.getValue())) customKeyValue.setValue(params.getValue());
        if (StringUtils.isNotEmpty(params.getRemarks())) customKeyValue.setRemarks(params.getRemarks());
        customKeyValue.setModifier(session.getUserId());

        customKeyValueDao.save(customKeyValue);
        return jsonDataHolder.simpleMsg(customKeyValue.getId(), MsgType.update);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonDataHolder delete(@RequestParam(value = "id", defaultValue = "") int id
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();

        CustomKeyValue customKeyValue = customKeyValueDao.getById(id);
        if (customKeyValue == null) return jsonDataHolder.error404();

        customKeyValueDao.delete(customKeyValue);

        return jsonDataHolder.simpleMsg(customKeyValue.getId(), MsgType.delete);
    }


}
