package com.zjicm.teacher.service;


import com.zjicm.dto.Page;
import com.zjicm.teacher.domain.Teacher;

import java.util.List;

public interface TeacherService {

    /**
     * 保存或更新
     *
     * @param col
     */
    void save(Teacher col);

    /**
     * 通过主键 ID 获取教师信息
     * @param id
     * @return
     */
    Teacher get(Integer id);

    /**
     * 通过 工号 获取教师信息
     * @param number
     * @return
     */
    Teacher getByNum(String number);


    void updateImgUrl(String id, String url);

    List<Teacher> find(int offset, int length);

    /**
     * 查看院系用户的分页信息【管理员页面需要用到】
     *
     * @param pageSize
     * @param page
     * @return
     */
    Page pageForCollege(int pageSize, int page);

    //更新院系用户的照片
    boolean updateColImg(String id, String url);

    //更新院系用户信息
    void updateCol(Teacher col);

    //获得一个院系用户的信息
    Teacher getCol(String id);


}
