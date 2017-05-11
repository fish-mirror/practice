package com.zjicm.api.user.auth;

import com.zjicm.auth.beans.AccountUserOut;
import com.zjicm.auth.beans.AuthorityRouteSupport;
import com.zjicm.auth.enums.Role;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonDataHolder;
import com.zjicm.common.web.RootController;
import com.zjicm.company.domain.Company;
import com.zjicm.student.domain.Student;
import com.zjicm.teacher.domain.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * 个人信息接口
 * <p>
 * Created by yujing on 2017/5/10.
 */
@Controller
@RequestMapping("/user/i/account")
public class AccountUserApi extends RootController {


    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public JsonDataHolder get(HttpServletRequest request) {
        JsonDataHolder jsonDataHolder = new JsonDataHolder();
        UserSession session = getUserSession(request);
        Role role = Role.is(session.getRoleId());
        if (role == null) return jsonDataHolder.error403();

        AccountUserOut accountUserOut = new AccountUserOut(session.getUserId(),
                                                           session.getNumber(),
                                                           session.getRoleId());

        switch (role) {
            case company:
                Company company = companyService.getByNum(session.getNumber());
                accountUserOut.setCompany(company);
                accountUserOut.setRoutes(AuthorityRouteSupport.getCompanyRoutes());
                break;
            case student:
                Student student = studentService.getByNum(session.getNumber());
                accountUserOut.setStudent(student);
                accountUserOut.setRoutes(AuthorityRouteSupport.getStudentRoutes());
                break;
            case teacher:
                Teacher teacher = teacherService.getByNum(session.getNumber());
                Set<Integer> authorties = session.getAuthorities();
                accountUserOut.setTeacher(teacher);
                accountUserOut.setRoutes(AuthorityRouteSupport.getTeacherRoutes(authorties));
                break;
            default:
                break;
        }

        return jsonDataHolder.addToItems(accountUserOut);
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
