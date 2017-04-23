package com.zjicm.cooperation.beans;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 合作意向修改参数
 * <p>
 * Created by yujing on 2017/4/23.
 */
public class IntentionPatchParams {
    @NotNull
    @Range(min = 1, message = "请输入合法主键 ID")
    private int id;
    @Length(max = 30)
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
