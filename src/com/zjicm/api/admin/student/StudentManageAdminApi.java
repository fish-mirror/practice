package com.zjicm.api.admin.student;

import com.zjicm.common.lang.json.JsonDataHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * 学生信息后台管理接口
 * 分权限粒度 本学院／跨学院／全校学生数据的操作
 *
 * Created by yujing on 2017/3/30.
 */
@Controller
@RequestMapping(value = "/admin/i/student")
public class StudentManageAdminApi {


    @Value("${file.upload.base}")
    private String path;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder analyzeAndCreate(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestParam(value = "id", defaultValue = "0", required = false) int attId
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (attId < 0) return jsonDataHolder.error400();


        File xls = new File(path + "/" + "");


        return null;
    }

}
