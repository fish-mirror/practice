package com.zjicm.api.admin;

import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.shortterm.service.ShortTermSelectService;
import com.zjicm.web.admin.AdminBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 短学期后台管理接口
 * <p>
 * Created by yujing on 2017/5/13.
 */
@Controller
@RequestMapping("/admin/i/shortterm")
public class ShortTermAdminApi extends AdminBaseController {

    @Autowired
    private ShortTermSelectService shortTermSelectService;

    /**
     * 打开/关闭 短学期系统选课状态
     *
     * @param request
     * @param open
     * @param institute 指定学院
     * @return
     */
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder openSelect(HttpServletRequest request,
                                     @RequestParam(value = "open", defaultValue = "true", required = false) boolean open,
                                     @RequestParam(value = "institute", defaultValue = "0", required = false) int institute

    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (institute <= 0) return jsonDataHolder.error400();

        if (open) shortTermSelectService.open(institute);
        else shortTermSelectService.close(institute);

        return jsonDataHolder.simpleMsg(institute, MsgType.update);

    }
}
