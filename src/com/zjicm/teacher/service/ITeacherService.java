package com.zjicm.teacher.service;


import com.zjicm.dto.Page;
import com.zjicm.teacher.domain.Teacher;

import java.util.List;

public interface ITeacherService {

    void save(Teacher col);

    Teacher get(String id);

    //更新院系用户信息
    void update(Teacher col);

    void updateImgUrl(String id, String url);

    List<Teacher> find(int offset, int length);

    int count();

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
