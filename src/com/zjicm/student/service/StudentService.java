package com.zjicm.student.service;

import java.util.List;
import java.util.Map;

import com.zjicm.student.dao.StudentDao;
import com.zjicm.dto.Page;
import com.zjicm.dto.StatusDTO;
import com.zjicm.student.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Page pageForStudentInfo(Short graduate, String classname, String num, int pageSize, int page) {
        return null;
    }

    @Override
    public List<String> getClassList(String institute) {
        return null;
    }

    @Override
    public List<String> getMajorList(String institute) {
        return null;
    }

    @Override
    public Map<String, StatusDTO> getStatus(String institute) {
        return null;
    }

    @Override
    public boolean updateStuImg(String id, String url) {
        return false;
    }

    @Override
    public void updateStu(Student stu) {

    }

    @Override
    public Student getStu(String id) {
        return null;
    }

    @Override
    public void save(Student stu) {

    }

    @Override
    public Student get(String id) {
        return null;
    }

    @Override
    public void update(Student stu) {

    }

    @Override
    public void updateImgUrl(String id, String url) {

    }

    @Override
    public List<Student> find(int offset, int length) {
        return null;
    }

    @Override
    public List<Student> findByClassName(String classname, int offset, int length) {
        return null;
    }

    @Override
    public List<Student> findGraduateClass(short graduate, int offset, int length) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int countByClassName(String classname) {
        return 0;
    }

    @Override
    public int countGraduateClass(short graduate) {
        return 0;
    }

    @Override
    public List countGraduateStatus(short graduate) {
        return null;
    }

    @Override
    public List countClassStatus(String classname) {
        return null;
    }

    @Override
    public List countInstituteStatus(String institute) {
        return null;
    }

    @Override
    public List<Student> findInGraduateStatus(short graduate, short status) {
        return null;
    }

    @Override
    public List<Student> findInClassStatus(String classname, short status) {
        return null;
    }

    @Override
    public List<Student> findInInstituteStatus(String institute, short status) {
        return null;
    }


//    public Page pageForStudentInfo(Short graduate, String classname, String num, int pageSize, int page) {
//
//        int count;
//        if (graduate != null) {
//            count = studentDao.countGraduateClass(graduate);
//        } else if (classname != null) {
//            count = studentDao.countByClassName(classname);
//        } else if (num != null) {
//            count = 1;
//        } else {
//            count = studentDao.count();
//        }
//        int totalPage = Page.countTotalPage(pageSize, count);
//        int offset = Page.countOffset(pageSize, page);
//        int length = pageSize;
//        int currentPage = Page.countCurrentPage(page);
//
//        List<Student> list;
//        if (graduate != null) {
//            list = studentDao.findGraduateClass(graduate, offset, length);
//        } else if (classname != null) {
//            list = studentDao.findByClassName(classname, offset, length);
//        } else if (num != null) {
//            list = new ArrayList(0);
//            list.add(studentDao.get(num));
//        } else {
//            list = studentDao.find(offset, length);
//        }
//
//
//        Page p = new Page();
//        p.setPageSize(pageSize);
//        p.setCurrentPage(currentPage);
//        p.setAllRow(count);
//        p.setTotalPage(totalPage);
//        p.setList(list);
//        p.init();
//
//        return p;
//    }
//
//    public List<String> getClassList(String institute) {
//        return studentDao.getClassList(institute);
//    }
//
//    public List<String> getMajorList(String institute) {
//        return studentDao.getMajorList(institute);
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public Map<String, StatusDTO> getStatus(String institute) {
//        List<Object[]> list;
//        Map<String, StatusDTO> map = new HashMap<String, StatusDTO>(0);
//        //学院的状态分布
//        list = studentDao.countInstituteStatus(institute);
//        map.put("institute", createStatusDTO(list));
//        //毕业班的状态分布
//        list = studentDao.countGraduateStatus((short) 1);
//        map.put("graduate", createStatusDTO(list));
//        //非毕业班的状态分布
//        list = studentDao.countGraduateStatus((short) 0);
//        map.put("non_graduate", createStatusDTO(list));
//        //各个班级的状态分布
//        List<String> classList = getClassList(institute);
//        for (String classname : classList) {
//            list = studentDao.countClassStatus(classname);
//            map.put(classname, createStatusDTO(list));
//        }
//        return map;
//    }
//
//    private StatusDTO createStatusDTO(List<Object[]> list) {
//        StatusDTO temp = new StatusDTO();
//        for (Object[] o : list) {
//            switch ((short) o[0]) {
//                case 0:
//                    temp.setNo_practice(((Long) o[1]).intValue());
//                    break;
//                case 1:
//                    temp.setPracticing(((Long) o[1]).intValue());
//                    break;
//                case 2:
//                    temp.setPractice_graded(((Long) o[1]).intValue());
//                    break;
//                case 3:
//                    temp.setPracticed(((Long) o[1]).intValue());
//                    break;
//            }
//        }
//        return temp;
//    }
//
//    @Override
//    public boolean updateStuImg(String id, String url) {
//        try {
//            studentDao.updateImgUrl(id, url);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void updateStu(Student stu) {
//        studentDao.update(stu);
//    }
//
//    @Override
//    public Student getStu(String id) {
//        return studentDao.get(id);
//    }

}