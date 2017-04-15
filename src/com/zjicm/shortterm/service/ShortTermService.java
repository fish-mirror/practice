package com.zjicm.shortterm.service;

import com.zjicm.shortterm.domain.ShortTermComment;
import com.zjicm.shortterm.domain.ShortTermProject;
import com.zjicm.shortterm.domain.ShortTermReport;
import com.zjicm.shortterm.dao.ShortTermCommentDao;
import com.zjicm.shortterm.dao.ShortTermProjectDao;
import com.zjicm.shortterm.dao.ShortTermReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yujing on 2017/1/3.
 */
@Component
public class ShortTermService implements IShortTermService {

    @Autowired
    private ShortTermProjectDao shortTermProjectDao;
    @Autowired
    private ShortTermReportDao shortTermReportDao;
    @Autowired
    private ShortTermCommentDao shortTermCommentDao;


    @Override
    public Integer addProject(ShortTermProject stp) {
        return null;
    }

    @Override
    public void updateProjectStatus(Integer id, Short status) {

    }

    @Override
    public void updateProject(ShortTermProject stp) {

    }

    @Override
    public ShortTermProject getProject(Integer id) {

        return shortTermProjectDao.getById(id);
    }

    @Override
    public List<ShortTermProject> getProjectList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ShortTermProject> getProjectList(String term) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ShortTermProject> getProjectList(Short status) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ShortTermProject> getProjectListByCol(String institute,
                                                      Short status) {
        return null;
}

    @Override
    public List<ShortTermProject> getProjectListByCom(String comId, Short status) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String addSelectProject(Integer pid, String stuId, String major, String insitute, String term) {
        return null;
//        String grade = (stuId).substring(0, 2);
//        ShortTermProject shortTerm = this.getProject(pid);
//        if (shortTermReportDao.get(stuId, pid) != null) {
//            return "已存在对应选课记录！";
//        }
//        if (shortTerm.getInstitute().equals(insitute)
//            && shortTerm.getStatus() == 1
//            && (term == null
//                || shortTerm.getTerm().equals(term))) {
//            //人数限制
//            Integer topNum = shortTerm.getTopNum();
//            Integer selectedNum = shortTerm.getSelectedNum();
//            Integer unmajorNum = shortTerm.getUnmajorNum();
//            Integer unmajorSelected = shortTerm.getUnmajorSelected();
//
//            if (topNum > selectedNum) {
//                //年级限制
//                if (shortTerm.getGradeNeed().equals("all") || shortTerm.getGradeNeed().equals(grade)) {
//                    //专业限制
//                    if (shortTerm.getMajorNeed().equals("all") || shortTerm.getMajorNeed().equals(major)) {
//                        shortTermReportDao.add(stuId, new ShortTermProject(pid));
//                        selectedNum++;
//                        shortTerm.setSelectedNum(selectedNum);
//                        shortTermProjectDao.update(shortTerm);
//                        return "success";
//                        //还有非专业名额
//                    } else if (unmajorSelected < unmajorNum) {
//                        shortTermReportDao.add(stuId, new ShortTermProject(pid));
//                        selectedNum++;
//                        unmajorSelected++;
//                        shortTerm.setSelectedNum(selectedNum);
//                        shortTerm.setUnmajorSelected(unmajorSelected);
//                        shortTermProjectDao.update(shortTerm);
//                        return "success";
//                    } else {
//                        return "专业限制，名额已满！";
//                    }
//                } else {
//                    return "年级不符！";
//                }
//
//            } else {
//                return "该项目人数已满！";
//            }
//        } else {
//            return "该项目不可选！";
//        }


    }

    @Override
    public boolean cancelSelectedProject(Integer id, String stuId, String term, String major) {
//        ShortTermReport str = this.getReport(id);
//        //判断是否是有效的学生对象和学期
//        if (str != null && str.getStuId().equals(stuId) && str.getShortTermProject().getTerm().equals(term)) {
//            ShortTermProject stp = str.getShortTermProject();
//            stp.setSelectedNum(stp.getSelectedNum() - 1);
//            if (stp.getMajorNeed().equals("all") || stp.getMajorNeed().equals(major)) {
//
//            } else {
//                stp.setUnmajorSelected(stp.getUnmajorSelected() - 1);
//            }
//            shortTermProjectDao.update(stp);
//            shortTermReportDao.delete(str);
//            return true;
//        } else {
//            return false;
//        }
        return false;

    }

    @Override
    public ShortTermReport getReport(Integer id) {
        return null;
    }

    @Override
    public ShortTermReport getProjectSelected(String stuId, String term) {
        return null;
    }

    @Override
    public List<ShortTermReport> getReportList(String stuId) {
        return null;
    }


    @Override
    public List<ShortTermReport> getReportList(String institute, String comId) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void addCommemt(Integer rid, String userId, float grade) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateComment(Integer id, float grade) {
        // TODO Auto-generated method stub

    }

    @Override
    public Float getGrade(Integer rid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ShortTermComment> getGradeList(Integer rid) {
        // TODO Auto-generated method stub
        return null;
    }



}
