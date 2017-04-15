package com.zjicm.student.support;

import com.zjicm.auth.service.UserService;
import com.zjicm.common.lang.excel.XlsReader;
import com.zjicm.student.service.StudentService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 学生信息业务支持
 * Created by yujing on 2017/3/30.
 */
@Service
public class StudentInfoSuppport {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;

    /**
     * 从 EXCEL 文件解析并导入学生信息
     * @param xls
     * @return
     */
    public String importStudentInfo(File xls) {
        XlsReader xlsReader = new XlsReader();
        try {
            Map<Integer, List<String>> map = xlsReader.readExcelContent(new FileInputStream(xls));
            map.forEach((line, values) -> {
                Long simuid = NumberUtils.toLong(values.get(0), -1);
                // 先创建用户信息

                // 再创建学生信息

            });


        } catch (IOException e) {
            e.printStackTrace();
            return  "文件地址无效";
        }
        return null;
    }
}
