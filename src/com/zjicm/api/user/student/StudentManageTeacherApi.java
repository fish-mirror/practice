package com.zjicm.api.user.student;

import com.zjicm.attachment.domain.Attachment;
import com.zjicm.attachment.service.AttachmentService;
import com.zjicm.auth.enums.AuthEnums;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.excel.XlsReader;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.lang.json.MsgType;
import com.zjicm.common.lang.util.StringUtil;
import com.zjicm.student.beans.StudentInstituteParams;
import com.zjicm.student.domain.Student;
import com.zjicm.student.service.StudentService;
import com.zjicm.web.teacher.TeacherBaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 学生管理接口
 * Created by yujing on 2017/3/30.
 */
@Controller
@RequestMapping(value = "/teacher/i/student")
public class StudentManageTeacherApi extends TeacherBaseController {

    @Value("${file.upload.base}/public")
    private String publicPath;

    @Autowired
    private StudentService studentService;
    @Autowired
    private AttachmentService attachmentService;

    /**
     * 导入学生信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonDataHolder create(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(value = "id", defaultValue = "0", required = false) int attId
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (attId < 0) return jsonDataHolder.error400();
        UserSession session = getUserSession(request);
        Attachment attachment = attachmentService.getById(attId, session.getUserId());
        if (attachment == null || StringUtils.isBlank(attachment.getPath())) return jsonDataHolder.error404();


        File xls = new File(publicPath + "/" + attachment.getPath());
        XlsReader xlsReader = new XlsReader();
        StringBuilder sb = new StringBuilder();
        try {
            Map<Integer, List<String>> map = xlsReader.readExcelContent(new FileInputStream(xls));

            map.forEach((line, values) -> {
                if (!studentService.createStudentFromExcel(values, session.getInstitute())) {
                    sb.append("第 " + line + " 行导入失败 </br>\n");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            jsonDataHolder.putToError(404, "文件地址无效");
        }
        if (StringUtil.isNotBlank(sb.toString())) return jsonDataHolder.putToError(203, sb.toString());
        return jsonDataHolder.simpleMsg(0, MsgType.add);
    }

    /**
     * 单字段更新学生的学院信息
     *
     * @param request
     * @param respons
     * @param params
     * @param results
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ResponseBody
    public JsonDataHolder patch(HttpServletRequest request,
                                HttpServletResponse respons,
                                @RequestParam(value = "number", defaultValue = "", required = false) String number,
                                @Valid @ModelAttribute StudentInstituteParams params,
                                BindingResult results
    ) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        if (StringUtil.isBlank(number)) return jsonDataHolder.error400();

        if (jsonDataHolder.checkError(results)) return jsonDataHolder;

        UserSession session = getUserSession(request);
        Student student = studentService.getByNum(number);

        if (student == null) return jsonDataHolder.error101();
        if (student.getInstitute() != session.getInstitute()) return jsonDataHolder.error403();

        studentService.patchStudentByTeacher(student, params);

        return jsonDataHolder.simpleMsg(student.getId(), MsgType.update);

    }
    @Override
    public int permissionToCheck() {
        return AuthEnums.student_manage.getValue();
    }
}
