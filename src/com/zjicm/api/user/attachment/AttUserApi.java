package com.zjicm.api.user.attachment;

import com.zjicm.attachment.beans.AttUserOut;
import com.zjicm.attachment.domain.Attachment;
import com.zjicm.attachment.enums.AttEnums;
import com.zjicm.attachment.service.AttachmentService;
import com.zjicm.attachment.service.AttUploadService;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.common.web.RootController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件信息逻辑
 * <p/>
 * Created by yujing on 2017/4/15.
 */
@Controller
@RequestMapping(value = "/user/i/att")
public class AttUserApi extends RootController {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private AttUploadService attUploadSupport;

    /**
     * 文件列表获取
     *
     * @param request
     * @param response
     * @param typeVal        文件类型
     * @param page_index     第几页
     * @param items_per_page 每页数量
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder list(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(value = "type", defaultValue = "1", required = false) int typeVal,
                               @RequestParam(value = "page_index", defaultValue = "1", required = false) int page_index,
                               @RequestParam(value = "items_per_page", defaultValue = "5", required = false) int items_per_page
    ) {
        UserSession session = getUserSession(request);
        int userId = session.getUserId();

        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        AttEnums.Type type = AttEnums.Type.is(typeVal);

        PageResult<Attachment> pr = attachmentService.page(userId, type, page_index, items_per_page);

        if (pr == null || pr.getResult() == null || pr.getResult().size() <= 0) {
            return jsonDataHolder.error101();
        }

        jsonDataHolder.putAttrToJsonDataHolder(pr);
         pr.getResult().forEach(att -> jsonDataHolder.addToItems(new AttUserOut(att)));

        return jsonDataHolder;
    }

    /**
     * 文件信息获取
     *
     * @param request
     * @param response
     * @param ids
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder get(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam(value = "id", defaultValue = "") List<Integer> ids
    ) {
        UserSession session = getUserSession(request);
        int userId = session.getUserId();

        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (CollectionUtils.isEmpty(ids)) return jsonDataHolder.error400();

        List<Attachment> atts = attachmentService.getListByAuth(ids, userId);

        if (CollectionUtils.isEmpty(atts)) return jsonDataHolder.error101();

        atts.forEach(att -> jsonDataHolder.addToItems(new AttUserOut(att)));
        return jsonDataHolder;
    }


    /**
     * 文件上传
     *
     * @param request
     * @param response
     * @param files     文件信息
     * @param type      文件类型
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder upload(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(value = "attachment", required = false) MultipartFile[] files,
                                 @RequestParam(value = "type", defaultValue = "", required = false) int type
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();

        if (ArrayUtils.isEmpty(files)) return jsonDataHolder.error400();

        // 文件所属业务类型检测
        AttEnums.Type attType = AttEnums.Type.is(type);
        if (attType == null) return jsonDataHolder.error403();

        UserSession session = getUserSession(request);
        int userId = session.getUserId();

        List<Attachment> atts = attUploadSupport.upload(files, attType, false, userId);
        if (CollectionUtils.isEmpty(atts)) return jsonDataHolder.error101();

        atts.forEach(tmpAtt -> jsonDataHolder.addToItems(new AttUserOut(tmpAtt)));
        return jsonDataHolder;
    }
}
