package com.zjicm.shortterm.service;

import com.zjicm.shortterm.domain.ShortTermComment;
import com.zjicm.shortterm.domain.ShortTermProject;
import com.zjicm.shortterm.domain.ShortTermReport;
import com.zjicm.shortterm.dao.ShortTermCommentDao;
import com.zjicm.shortterm.dao.ShortTermProjectDao;
import com.zjicm.shortterm.dao.ShortTermReportDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yujing on 2017/1/3.
 */
@Component
public class ShortTermService implements IShortTermService {

    private ShortTermProjectDao stpDao;
    private ShortTermReportDao strDao;
    private ShortTermCommentDao stcDao;

    @Override
    public Integer addProject(ShortTermProject stp) {
        return stpDao.add(stp);
    }

    @Override
    public void updateProjectStatus(Integer id, Short status) {
        stpDao.updateStatus(id, status);
    }

    @Override
    public void updateProject(ShortTermProject stp) {
        stpDao.update(stp);
    }

    @Override
    public ShortTermProject getProject(Integer id) {

        return stpDao.get(id);
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
        return stpDao.getList(status, institute, null, null);
    }

    @Override
    public List<ShortTermProject> getProjectListByCom(String comId, Short status) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String addSelectProject(Integer pid, String stuId, String major, String insitute, String term) {
        String grade = (stuId).substring(0, 2);
        ShortTermProject shortTerm = this.getProject(pid);
        if (strDao.get(stuId, pid) != null) {
            return "已存在对应选课记录！";
        }
        if (shortTerm.getInstitute().equals(insitute)
            && shortTerm.getStatus() == 1
            && (term == null
                || shortTerm.getTerm().equals(term))) {
            //人数限制
            Integer topNum = shortTerm.getTopNum();
            Integer selectedNum = shortTerm.getSelectedNum();
            Integer unmajorNum = shortTerm.getUnmajorNum();
            Integer unmajorSelected = shortTerm.getUnmajorSelected();

            if (topNum > selectedNum) {
                //年级限制
                if (shortTerm.getGradeNeed().equals("all") || shortTerm.getGradeNeed().equals(grade)) {
                    //专业限制
                    if (shortTerm.getMajorNeed().equals("all") || shortTerm.getMajorNeed().equals(major)) {
                        strDao.add(stuId, new ShortTermProject(pid));
                        selectedNum++;
                        shortTerm.setSelectedNum(selectedNum);
                        stpDao.update(shortTerm);
                        return "success";
                        //还有非专业名额
                    } else if (unmajorSelected < unmajorNum) {
                        strDao.add(stuId, new ShortTermProject(pid));
                        selectedNum++;
                        unmajorSelected++;
                        shortTerm.setSelectedNum(selectedNum);
                        shortTerm.setUnmajorSelected(unmajorSelected);
                        stpDao.update(shortTerm);
                        return "success";
                    } else {
                        return "专业限制，名额已满！";
                    }
                } else {
                    return "年级不符！";
                }

            } else {
                return "该项目人数已满！";
            }
        } else {
            return "该项目不可选！";
        }


    }

    @Override
    public boolean cancelSelectedProject(Integer id, String stuId, String term, String major) {
        ShortTermReport str = this.getReport(id);
        //判断是否是有效的学生对象和学期
        if (str != null && str.getStuId().equals(stuId) && str.getShortTermProject().getTerm().equals(term)) {
            ShortTermProject stp = str.getShortTermProject();
            stp.setSelectedNum(stp.getSelectedNum() - 1);
            if (stp.getMajorNeed().equals("all") || stp.getMajorNeed().equals(major)) {

            } else {
                stp.setUnmajorSelected(stp.getUnmajorSelected() - 1);
            }
            stpDao.update(stp);
            strDao.delete(str);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public ShortTermReport getReport(Integer id) {

        return strDao.get(id);
    }

    @Override
    public ShortTermReport getProjectSelected(String stuId, String term) {

        return strDao.get(stuId, term);
    }

    @Override
    public List<ShortTermReport> getReportList(String stuId) {

        return strDao.getListByStu(stuId);
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

    public ShortTermProjectDao getStpDao() {
        return stpDao;
    }

    @Resource
    public void setStpDao(ShortTermProjectDao stpDao) {
        this.stpDao = stpDao;
    }

    public ShortTermReportDao getStrDao() {
        return strDao;
    }

    @Resource
    public void setStrDao(ShortTermReportDao strDao) {
        this.strDao = strDao;
    }

    public ShortTermCommentDao getStcDao() {
        return stcDao;
    }

    @Resource
    public void setStcDao(ShortTermCommentDao stcDao) {
        this.stcDao = stcDao;
    }


}
