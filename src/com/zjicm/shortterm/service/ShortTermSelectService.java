package com.zjicm.shortterm.service;

import com.zjicm.cache.consts.CacheConsts;
import com.zjicm.cache.servive.CacheService;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.customkeyvalue.dao.CustomKeyValueDao;
import com.zjicm.customkeyvalue.domain.CustomKeyValue;
import com.zjicm.customkeyvalue.enums.CustomKey;
import com.zjicm.customkeyvalue.service.CustomKeyValueService;
import com.zjicm.shortterm.dao.ShortTermProjectDao;
import com.zjicm.shortterm.dao.ShortTermReportDao;
import com.zjicm.shortterm.domain.ShortTermProject;
import com.zjicm.shortterm.domain.ShortTermReport;
import com.zjicm.shortterm.enums.ShortTermEnums;
import com.zjicm.student.support.CollegeInfoSupport;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 短学期选课相关业务逻辑
 * <p>
 * Created by yujing on 2017/5/13.
 */
@Service
public class ShortTermSelectService {

    private static Map<Integer, List<ShortTermProject>> canSelectProjectsMap = new HashMap<>();

    @Autowired
    private ShortTermProjectDao shortTermProjectDao;
    @Autowired
    private ShortTermReportDao shortTermReportDao;
    @Autowired
    private CustomKeyValueDao customKeyValueDao;


    @Autowired
    private ShortTermInfoService shortTermInfoService;
    @Autowired
    private CacheService cacheService;

    /**
     * 打开系统中的可选课开关
     *
     * @return
     */
    public boolean open(int institute) {
        CustomKeyValue customKeyValue = customKeyValueDao.getByKey(CustomKey.short_term_can_select.name());
        if (customKeyValue.getValue().equals(String.valueOf(true))) return true;
        customKeyValue.setValue(String.valueOf(true));

        List<ShortTermProject> projects = shortTermProjectDao.getAllCanSelected(institute,
                                                                                CollegeInfoSupport.getCurrentTerm());
        getCanSelectProjectsMap().put(institute, projects);
        return true;
    }

    /**
     * 关闭可选课开关
     *
     * @param institute
     * @return
     */
    public boolean close(int institute) {
        CustomKeyValue customKeyValue = customKeyValueDao.getByKey(CustomKey.short_term_can_select.name());
        if (customKeyValue.getValue().equals(String.valueOf(false))) return true;
        customKeyValue.setValue(String.valueOf(false));

        getCanSelectProjectsMap().remove(institute);
        return true;
    }

    /**
     * 是否可选
     *
     * @return
     */
    public boolean canSelectStatus() {
        String value = CustomKeyValueService.getValue(CustomKey.short_term_can_select.name());
        if (StringUtils.isNotBlank(value) && value.equals("true")) return true;

        return false;
    }

    /**
     * 从可选列表中获取项目
     *
     * @param id
     * @param institute
     * @return
     */
    public ShortTermProject getProjectFromCanSelect(int id, int institute) {
        List<ShortTermProject> projects = getCanSelectProjectsMap().get(institute);
        if (CollectionUtils.isEmpty(projects)) return null;

        return projects.stream().filter(project -> project.getId() == id).findFirst().orElse(null);
    }

    /**
     * 分页获取可选课程列表
     *
     * @param institute
     * @param page
     * @param size
     * @return
     */
    public PageResult<ShortTermProject> getShortTermProject(int institute, int page, int size) {
        List<ShortTermProject> projects = getCanSelectProjectsMap().get(institute);
        if (CollectionUtils.isEmpty(projects)) return null;

        int total = projects.size();
        PageResult<ShortTermProject> pageResult = new PageResult<>();
        pageResult.setParameters(total, page, size);
        List<ShortTermProject> projectList = projects.stream()
                                                     .skip(pageResult.getStart())
                                                     .limit(pageResult.getLimit())
                                                     .collect(Collectors.toList());
        pageResult.setResult(projectList);
        return pageResult;
    }

    /**
     * 选课
     *
     * @param project
     * @param session
     * @return
     */
    public synchronized String selectProject(ShortTermProject project, UserSession session) {
        if (getCurrentSelectedProject(session.getNumber()) != null) return "已完成短学期选课，请勿多次选课";

        if (project.getStatus() != ShortTermEnums.ProjectStatus.can_selected.getValue()) return "该项目状态不可选";
        if (StringUtils.isNotBlank(project.getGradeNeed()) &&
            !project.getGradeNeed().contains(String.valueOf(session.getGrade()))) {
            return "年级与规定不符";
        }

        // 人数限制
        int topNum = project.getTopNum();
        int selectedNum = project.getSelectedNum();
        int unmajorNum = project.getUnmajorNum();
        int unmajorSelected = project.getUnmajorSelected();
        if (topNum == selectedNum) return "该项目人数已满";


        int recordType = 0;

        // 专业无限制
        if (StringUtils.isBlank(project.getMajorNeed())) {
            // 已选 +1
            project.setSelectedNum(++selectedNum);
            recordType = ShortTermEnums.RecordType.major.getValue();

        }
        // 专业限制，但有跨专业名额
        else if (project.getMajorNeed().equals(session.getMajor())) {
            recordType = ShortTermEnums.RecordType.major.getValue();

            // 专业名额优先于跨专业名额
            if (selectedNum + unmajorNum >= topNum) {
                project.setUnmajorSelected(++unmajorSelected);
                recordType = ShortTermEnums.RecordType.major_consume_unmajor.getValue();
            }
            project.setSelectedNum(++selectedNum);

        }
        // 专业限制，但有跨专业名额
        else if (unmajorSelected < unmajorNum) {
            // 已选 +1，已选跨专业名额 +1
            project.setSelectedNum(++selectedNum);
            project.setUnmajorSelected(++unmajorSelected);
            recordType = ShortTermEnums.RecordType.unmajor.getValue();
        } else return "专业限制，名额已满";

        shortTermProjectDao.save(project);
        // 保存 10 分钟缓存
        cacheService.set(CacheConsts.Storage.SHORT_TERM, "short_term_project_" + session.getNumber(), project, 10 * 60);
        ShortTermReport report = new ShortTermReport(session.getNumber(), project.getId(), recordType);
        shortTermReportDao.save(report);
        return null;
    }


    /**
     * 取消选课
     *
     * @param project
     * @param session
     * @return
     */
    public synchronized boolean cancalProject(ShortTermProject project, UserSession session) {
        if (getCurrentSelectedProject(session.getNumber()) == null) return false;

        // 检验项目状态
        if (project.getStatus() != ShortTermEnums.ProjectStatus.can_selected.getValue()) return false;

        // 删除记录，更改数值
        int selectedNum = project.getSelectedNum();
        int unmajorSelected = project.getUnmajorSelected();
        ShortTermReport report = shortTermInfoService.getReport(project.getId(), session.getNumber());
        ShortTermEnums.RecordType recordType = ShortTermEnums.RecordType.is(report.getType());
        if (recordType == null) return false;

        switch (recordType) {
            case major:
                project.setSelectedNum(--selectedNum);
                break;
            case major_consume_unmajor:
            case unmajor:
                project.setSelectedNum(--selectedNum);
                project.setUnmajorSelected(--unmajorSelected);
                break;
            default:
                break;
        }
        shortTermReportDao.delete(report);
        cacheService.clear(CacheConsts.Storage.SHORT_TERM, "short_term_project_" + session.getNumber());
        return true;
    }

    /**
     * 获取当前已选项目
     *
     * @param studentNumber
     * @return
     */
    public ShortTermProject getCurrentSelectedProject(String studentNumber) {
        ShortTermProject project = cacheService.get(CacheConsts.Storage.SHORT_TERM,
                                                    "short_term_project_" + studentNumber);
        if (project == null) {
            project = shortTermInfoService.getProjectSelected(studentNumber, CollegeInfoSupport.getCurrentTerm());
        }
        return project;
    }


    public static Map<Integer, List<ShortTermProject>> getCanSelectProjectsMap() {
        return canSelectProjectsMap;
    }

    public static void setCanSelectProjectsMap(Map<Integer, List<ShortTermProject>> canSelectProjectsMap) {
        ShortTermSelectService.canSelectProjectsMap = canSelectProjectsMap;
    }
}
