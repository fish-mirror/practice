package com.zjicm.student.service;

import java.util.List;
import java.util.stream.Collectors;

import com.zjicm.auth.dao.UserDao;
import com.zjicm.auth.domain.User;
import com.zjicm.auth.enums.Role;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.student.beans.ClassInfoDto;
import com.zjicm.student.beans.ClassInfoOut;
import com.zjicm.student.beans.StatusDto;
import com.zjicm.student.beans.StatusOut;
import com.zjicm.student.dao.StudentDao;
import com.zjicm.student.domain.Student;
import com.zjicm.student.support.CollegeInfoSupport;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private UserDao userDao;
    /**
     * 保存
     *
     * @param student
     */
    public void save(Student student) {
        studentDao.save(student);
    }

    /**
     * 通过主键 ID 获取
     *
     * @param id
     * @return
     */
    public Student get(Integer id) {
        return studentDao.getById(id);
    }

    /**
     * 通过学号获取
     *
     * @param number
     * @return
     */
    public Student getByNum(String number) {
        return studentDao.getByField("number", number);
    }

    /**
     * 获得该学院的班级列表
     *
     * @param institute
     * @return
     */
    public List<ClassInfoOut> getClassList(int institute) {
        List<ClassInfoDto> list = studentDao.getClassInfoByInstitute(institute);
        if (CollectionUtils.isEmpty(list)) return null;

        List<ClassInfoOut> outs = list.stream().map(classInfoDto -> {
            ClassInfoOut out = new ClassInfoOut(classInfoDto);
            out.setClassname(CollegeInfoSupport.getClassName(String.valueOf(out.getGrade()),
                                                             out.getMajor(),
                                                             out.getClassIndex()));
            return out;
        }).collect(Collectors.toList());
        return outs;
    }

    /**
     * 获取学生的分页信息
     *
     * @param institute
     * @param isGraduated
     * @param grade
     * @param major
     * @param classIndex
     * @param status
     * @param name
     * @param page
     * @param size
     * @return
     */
    public PageResult<Student> pageStudent(int institute,
                                           boolean isGraduated,
                                           int grade,
                                           String major,
                                           int classIndex,
                                           int status,
                                           String name,
                                           int page,
                                           int size) {
        if (isGraduated) {
            grade = CollegeInfoSupport.getGraduatingGrade();
        }
        return studentDao.page(institute, grade, major, classIndex, status, name, page, size);
    }

    /**
     * 获取实习状态分布
     *
     * @param institute
     * @param isGraduated
     * @param grade
     * @param major
     * @param classIndex
     * @return
     */
    public List<StatusOut> getStatusdistribute(int institute,
                                               boolean isGraduated,
                                               int grade,
                                               String major,
                                               int classIndex) {
        if (isGraduated) {
            grade = CollegeInfoSupport.getGraduatingGrade();
        }

        List<StatusDto> list = studentDao.getStatusDistribute(institute, grade, major, classIndex);
        if (CollectionUtils.isEmpty(list)) return null;

        return list.stream().map(StatusOut::new).collect(Collectors.toList());
    }

    /**
     * 导入学生信息
     *
     * @param values
     * @param institute
     * @return
     */
    public boolean createStudentFromExcel(List<String> values, int institute) {
        if (CollectionUtils.isEmpty(values)) return false;
        String number = values.get(1);
        if (number.length() != 9 && !NumberUtils.isNumber(number)) return false;
        User user = new User(number, number, Role.student.getValue());

        Student student = new Student();
        student.setNumber(number);
        student.setName(values.get(2));
        student.setInstitute(institute);
        student.setMajor(CollegeInfoSupport.getMajorCode(values.get(4)));
        student.setClassIndex(CollegeInfoSupport.getClassIndex(values.get(5)));

        userDao.save(user);
        studentDao.save(student);
        return true;
    }
}
