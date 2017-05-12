package com.zjicm.shortterm.service;

import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.company.domain.Company;
import com.zjicm.company.service.CompanyService;
import com.zjicm.shortterm.beans.ProjectParams;
import com.zjicm.shortterm.beans.ProjectPatchParams;
import com.zjicm.shortterm.domain.ShortTermComment;
import com.zjicm.shortterm.domain.ShortTermProject;
import com.zjicm.shortterm.domain.ShortTermReport;
import com.zjicm.shortterm.dao.ShortTermCommentDao;
import com.zjicm.shortterm.dao.ShortTermProjectDao;
import com.zjicm.shortterm.dao.ShortTermReportDao;
import com.zjicm.shortterm.enums.ShortTermEnums;
import com.zjicm.student.support.CollegeInfoSupport;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 短学期业务方法
 *
 * Created by yujing on 2017/1/3.
 */
@Component
public class ShortTermService {

    @Autowired
    private ShortTermProjectDao shortTermProjectDao;
    @Autowired
    private ShortTermReportDao shortTermReportDao;
    @Autowired
    private ShortTermCommentDao shortTermCommentDao;
    @Autowired
    private CompanyService companyService;


    /**
     * 新增短学期项目
     *
     * @param params
     * @param session
     * @return
     */
    public Integer createProject(ProjectParams params, UserSession session) {
        if (params == null || session == null || session.getUserId() <= 0) return null;

        Company company = null;
        if (StringUtils.isNotBlank(params.getCompany_number())) {
            company = companyService.getByNum(params.getCompany_number());
        }

        ShortTermProject project = new ShortTermProject();
        project.setCreator(session.getUserId());
        project.setInstitute(session.getInstitute());
        project.setStatus(ShortTermEnums.ProjectStatus.cancel.getValue());
        project.setTerm(CollegeInfoSupport.getCurrentTerm());

        project.setAttId(params.getAtt_id());
        project.setCompany(company);
        project.setContent(params.getContent());
        project.setGradeNeed(params.getGrade_need());
        project.setMajorNeed(params.getMajor_need());
        project.setName(params.getName());
        project.setPlace(params.getPlace());
        project.setPurpose(params.getPurpose());
        project.setTopNum(params.getTop_num());
        project.setUnmajorNum(params.getUnmajor_num());
        shortTermProjectDao.save(project);
        return project.getId();
    }

    /**
     * 编辑更新短学期项目
     *
     * @param params
     * @param project
     */
    public String updateProject(ProjectPatchParams params, ShortTermProject project) {
        if (params == null || project == null) return "参数为空";
        if (params.getAtt_id() > 0) project.setAttId(params.getAtt_id());
        if (params.getTop_num() > 0) {
            if (project.getSelectedNum() > params.getTop_num()) return "设置的总数不能小于当前已选的数量";
            project.setTopNum(params.getTop_num());
        }
        if (params.getUnmajor_num() > 0) {
            if (project.getUnmajorSelected() > params.getUnmajor_num()) return "设置的跨专业名额不能小于当前已选数量";
            project.setUnmajorNum(params.getUnmajor_num());
        }

        if (StringUtils.isNotEmpty(params.getGrade_need())) project.setGradeNeed(params.getGrade_need());
        if (StringUtils.isNotBlank(params.getName())) project.setName(params.getName());
        if (StringUtils.isNotBlank(params.getContent())) project.setContent(params.getContent());
        if (StringUtils.isNotBlank(params.getMajor_need())) project.setMajorNeed(params.getMajor_need());
        if (StringUtils.isNotBlank(params.getPlace())) project.setPlace(params.getPlace());
        if (StringUtils.isNotBlank(params.getPurpose())) project.setPurpose(params.getPurpose());

        shortTermProjectDao.save(project);
        return null;
    }

    /**
     * 更改短学期项目状态
     *
     * @param id
     * @param status
     * @param institute
     * @return
     */
    public int updateProjectStatus(int id, int status, int institute) {
        List<Criterion> criteria = new ArrayList<>();
        criteria.add(Restrictions.eq("id", id));
        criteria.add(Restrictions.eq("institute", institute));
        return shortTermProjectDao.fieldUpdate("status", status, criteria, null, null);
    }

    /**
     * 获取短学期项目
     *
     * @param id
     * @return
     */
    public ShortTermProject getProject(Integer id) {
        return shortTermProjectDao.getById(id);
    }

    /**
     * 短学期项目的分页方法
     *
     * @param institute
     * @param companyNumber
     * @param term
     * @param status
     * @param fullStatus
     * @param gradeNeed
     * @param majorNeed
     * @param page
     * @param size
     * @return
     */
    public PageResult<ShortTermProject> pageProjects(int institute,
                                                     String companyNumber,
                                                     String name,
                                                     String term,
                                                     ShortTermEnums.ProjectStatus status,
                                                     ShortTermEnums.ProjectFull fullStatus,
                                                     int gradeNeed,
                                                     String majorNeed,
                                                     int page,
                                                     int size) {
        List<Criterion> criteria = new ArrayList<>();
        if (institute > 0) criteria.add(Restrictions.eq("institute", institute));
        if (StringUtils.isNotBlank(companyNumber)) criteria.add(Restrictions.eq("companyNumber", companyNumber));
        if (StringUtils.isNotBlank(term)) criteria.add(Restrictions.eq("term", term));
        if (StringUtils.isNotBlank(name)) criteria.add(Restrictions.like("name", "%" + name + "%"));
        if (status != null) criteria.add(Restrictions.eq("status", status.getValue()));
        if (fullStatus != null) {
            switch (fullStatus) {
                case full:
                    criteria.add(Restrictions.eqProperty("selectedNum", "topNum"));
                    break;
                case unfull:
                    criteria.add(Restrictions.ltProperty("selectedNum", "topNum"));
                    break;
            }
        }
        if (gradeNeed > 0) {
            criteria.add(Restrictions.or(
                    Restrictions.like("gradeNeed", "%" +gradeNeed + "%"),
                    Restrictions.eq("gradeNeed", "")));
        }
        if (StringUtils.isNotBlank(majorNeed)) criteria.add(Restrictions.eq("majorNeed", majorNeed));


        List<Order> orders = new ArrayList<>();
        orders.add(Order.desc("id"));

        return shortTermProjectDao.getPageResult(criteria, orders, page, size);
    }


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

    public ShortTermReport getReport(Integer id) {
        return null;
    }

    public ShortTermReport getProjectSelected(String stuId, String term) {
        return null;
    }

    public List<ShortTermReport> getReportList(String stuId) {
        return null;
    }


    public List<ShortTermReport> getReportList(String institute, String comId) {
        // TODO Auto-generated method stub
        return null;
    }


    public void addCommemt(Integer rid, String userId, float grade) {
        // TODO Auto-generated method stub

    }

    public void updateComment(Integer id, float grade) {
        // TODO Auto-generated method stub

    }

    public Float getGrade(Integer rid) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<ShortTermComment> getGradeList(Integer rid) {
        // TODO Auto-generated method stub
        return null;
    }


}
