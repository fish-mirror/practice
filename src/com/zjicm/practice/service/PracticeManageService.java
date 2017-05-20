package com.zjicm.practice.service;

import com.zjicm.company.domain.Company;
import com.zjicm.cooperation.service.CooperationService;
import com.zjicm.practice.dao.PracticeCommentDao;
import com.zjicm.practice.dao.PracticeInfoDao;
import com.zjicm.practice.domain.PracticeComment;
import com.zjicm.practice.domain.PracticeInfo;
import com.zjicm.practice.enums.PracticeStatus;
import com.zjicm.student.dao.StudentDao;
import com.zjicm.student.enums.StudentEnums;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实习管理业务支持
 * <p>
 * Created by yujing on 2017/5/20.
 */
@Service
public class PracticeManageService {
    @Autowired
    private PracticeInfoDao practiceInfoDao;
    @Autowired
    private PracticeCommentDao practiceCommentDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private CooperationService cooperationService;

    /**
     * 同意实习申请
     *
     * @param practiceInfo
     * @param agree
     */
    public void agreePracticeApply(PracticeInfo practiceInfo, boolean agree) {
        if (practiceInfo == null) return;
        if (practiceInfo.getStatus() != PracticeStatus.unreview.getValue()) return;

        if (!agree) {
            practiceInfo.setStatus(PracticeStatus.rejected.getValue());
            practiceInfoDao.save(practiceInfo);
        } else {
            practiceInfo.setStatus(PracticeStatus.ok.getValue());
            // 更新学生实习状态信息
            practiceInfo.getStudent().setStatus(StudentEnums.Status.practicing.getValue());

            // 创建合作企业
            if (StringUtils.isBlank(practiceInfo.getCompanyNumber())) {
                Company company = cooperationService.createCampay(practiceInfo);
                if (company != null) practiceInfo.setCompanyNumber(company.getNumber());
            }
            studentDao.save(practiceInfo.getStudent());
            practiceInfoDao.save(practiceInfo);
        }

    }


    /**
     * 添加评论
     *
     * @param practiceInfo
     * @param userId
     * @param score
     * @param comment
     */
    public void addComment(PracticeInfo practiceInfo, int userId, int score, String comment) {
        if (practiceInfo == null || practiceInfo.getStudent() == null || userId <= 0 || score < 0) return;

        PracticeComment practiceComment = new PracticeComment();
        practiceComment.setPracticeId(practiceInfo.getId());
        practiceComment.setScore(score);
        practiceComment.setComment(comment);
        practiceComment.setStudentNumber(practiceInfo.getStudent().getNumber());
        practiceComment.setTeacherUserId(userId);

        practiceCommentDao.save(practiceComment);

    }
}
