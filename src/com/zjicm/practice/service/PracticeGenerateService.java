package com.zjicm.practice.service;

import com.zjicm.common.beans.UserSession;
import com.zjicm.practice.beans.PracticeParams;
import com.zjicm.practice.beans.PracticePatchParams;
import com.zjicm.practice.dao.PracticeInfoDao;
import com.zjicm.practice.domain.PracticeInfo;
import com.zjicm.practice.enums.PracticeStatus;
import com.zjicm.student.domain.Student;
import com.zjicm.student.enums.StudentEnums;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实习信息生成支持
 * <p>
 * Created by yujing on 2017/5/20.
 */
@Service
public class PracticeGenerateService {
    @Autowired
    private PracticeInfoDao practiceInfoDao;


    /**
     * 创建实习信息
     *
     * @param params
     * @param session
     * @return
     */
    public int createPracticeInfo(PracticeParams params, UserSession session) {
        if (params == null || session == null) return 0;

        PracticeInfo practiceInfo = new PracticeInfo();
        practiceInfo.setAddress(params.getAddress());
        practiceInfo.setCellphone(params.getCellphone());
        practiceInfo.setCity(params.getCity());
        practiceInfo.setCompanyName(params.getCompany_name());
        practiceInfo.setCompanyNumber(params.getCompany_number());
        practiceInfo.setJob(params.getJob());
        practiceInfo.setLinkman(params.getLinkman());
        practiceInfo.setProvince(params.getProvince());
        practiceInfo.setPurpose(params.getPurpose());
        practiceInfo.setStudent(new Student(session.getNumber()));
        practiceInfo.setStatus(PracticeStatus.unreview.getValue());
        practiceInfo.setCreator(session.getUserId());

        practiceInfoDao.save(practiceInfo);
        return practiceInfo.getId();
    }

    /**
     * 更新实习信息
     *
     * @param params
     * @param practiceInfo
     * @param userId
     */
    public void updatePracticeInfo(PracticePatchParams params, PracticeInfo practiceInfo, int userId) {
        if (params == null || practiceInfo == null) return;

        if (StringUtils.isNotBlank(practiceInfo.getAddress())) practiceInfo.setAddress(params.getAddress());
        if (StringUtils.isNotBlank(practiceInfo.getCellphone())) practiceInfo.setCellphone(params.getCellphone());
        if (StringUtils.isNotBlank(practiceInfo.getCity())) practiceInfo.setCity(params.getCity());
        if (StringUtils.isNotBlank(practiceInfo.getCompanyName())) practiceInfo.setCompanyName(params.getCompany_name());
        if (StringUtils.isNotBlank(practiceInfo.getCompanyNumber())) {
            practiceInfo.setCompanyNumber(params.getCompany_number());
        }
        if (StringUtils.isNotBlank(practiceInfo.getJob())) practiceInfo.setJob(params.getJob());
        if (StringUtils.isNotBlank(practiceInfo.getLinkman())) practiceInfo.setLinkman(params.getLinkman());
        if (StringUtils.isNotBlank(practiceInfo.getProvince())) practiceInfo.setProvince(params.getProvince());
        if (StringUtils.isNotBlank(practiceInfo.getPurpose())) practiceInfo.setPurpose(params.getPurpose());
        practiceInfo.setStatus(PracticeStatus.unreview.getValue());
        practiceInfo.setModifier(userId);

        practiceInfoDao.save(practiceInfo);
    }

    /**
     * 关联报告
     *
     * @param practiceInfo
     * @param attId
     */
    public void linkReport(PracticeInfo practiceInfo, int attId) {
        if (practiceInfo == null || attId <= 0) return;
        practiceInfo.setAttId(attId);
        practiceInfo.setStatus(PracticeStatus.can_score.getValue());
        practiceInfo.getStudent().setStatus(StudentEnums.Status.practiced.getValue());
        practiceInfoDao.save(practiceInfo);
    }

    /**
     * 根据操作状态是否允许更新实习信息
     *
     * @param status
     * @return
     */
    public boolean canUpdateInfo(int status) {
        PracticeStatus practiceStatus = PracticeStatus.is(status);
        if (practiceStatus == null) return false;

        switch (practiceStatus) {
            case unreview:
            case rejected:
                return true;
            default:
                return false;
        }
    }
}
