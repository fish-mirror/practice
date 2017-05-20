package com.zjicm.practice.service;

import com.zjicm.common.beans.NameValue;
import com.zjicm.common.beans.UserSession;
import com.zjicm.common.lang.json.JsonUtil;
import com.zjicm.common.lang.page.PageResult;
import com.zjicm.common.lang.util.HttpClientUtil;
import com.zjicm.practice.beans.LocationSinaTemp;
import com.zjicm.practice.beans.WeeklyDataParams;
import com.zjicm.practice.dao.PracticeDataDao;
import com.zjicm.practice.domain.PracticeData;
import com.zjicm.student.domain.Student;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 实习周记查询、生成支持
 * <p>
 * Created by yujing on 2017/5/21.
 */
@Service
public class WeeklyDataService {
    @Autowired
    private PracticeDataDao practiceDataDao;

    /**
     * 创建
     *
     * @param params
     * @param session
     * @return
     */
    public int createPracticeData(WeeklyDataParams params, UserSession session) {
        if (params == null || session == null) return 0;

        PracticeData practiceData = new PracticeData();
        practiceData.setProvince(params.getProvince());
        practiceData.setCity(params.getCity());
        practiceData.setContent(params.getContent());
        practiceData.setStudent(new Student(session.getNumber()));
        practiceData.setInstitute(session.getInstitute());
        practiceData.setCreateTime(new Date());
        practiceDataDao.save(practiceData);
        return practiceData.getId();

    }

    /**
     * 获取位置信息
     *
     * @param ip
     * @return
     */
    public LocationSinaTemp getLocation(String ip) {
        if (StringUtils.isBlank(ip)) return null;
        String json = HttpClientUtil.get("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + ip);
        if (StringUtils.isBlank(json)) return null;

        return JsonUtil.toObject(LocationSinaTemp.class, json);

    }

    /**
     * 实习周记的分页信息
     *
     * @param page
     * @param size
     * @return
     */
    public PageResult<PracticeData> page(int institute, int page, int size) {
        List<Criterion> criteria = new ArrayList<>(1);
        criteria.add(Restrictions.eq("institute", institute));

        List<Order> orders = new ArrayList<>(1);
        orders.add(Order.desc("id"));
        return practiceDataDao.getPageResult(criteria, orders, page, size);
    }

    /**
     * 按省获取最近 7 天的分页信息
     *
     * @param institute
     * @param province
     * @param page
     * @param size
     * @return
     */
    public PageResult<PracticeData> pageByRecentProvince(int institute, String province, int page, int size) {
        Calendar calendar = Calendar.getInstance();
        Date endDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date beginDate = calendar.getTime();

        List<Criterion> criteria = new ArrayList<>(3);
        criteria.add(Restrictions.eq("institute", institute));
        if (StringUtils.isNotBlank(province)) criteria.add(Restrictions.eq("province", province));
        criteria.add(Restrictions.between("createTime", beginDate, endDate));

        List<Order> orders = new ArrayList<>(1);
        orders.add(Order.desc("id"));
        return practiceDataDao.getPageResult(criteria, orders, page, size);
    }

    /**
     * 实习地理位置分布
     *
     * @param institute
     * @return
     */
    public List<NameValue<String, Integer>> locationMap(int institute) {
        Calendar calendar = Calendar.getInstance();
        Date endDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date beginDate = calendar.getTime();


        final List<NameValue<String, Integer>> nameValueList = new ArrayList<>();

        practiceDataDao.useConnection(connection -> {
            String sql1 = "SELECT  province, count(DISTINCT student_number) FROM practice_data " +
                          "WHERE institute = ? AND create_time BETWEEN ? AND ? " +
                          "GROUP BY province";
            try {
                PreparedStatement statement = connection.prepareStatement(sql1);
                statement.setInt(1, institute);
                statement.setDate(2, new java.sql.Date(beginDate.getTime()));
                statement.setDate(3, new java.sql.Date(endDate.getTime()));
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String province = resultSet.getString(1);
                    int count = resultSet.getInt(2);
                    nameValueList.add(new NameValue<>(province, count));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return nameValueList;

    }


}
