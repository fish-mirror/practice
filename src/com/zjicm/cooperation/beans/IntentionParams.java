package com.zjicm.cooperation.beans;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 合作意向创建参数
 * <p>
 * Created by yujing on 2017/4/23.
 */
public class IntentionParams {
    @NotNull
    @Length(max = 30)
    private String title;
    @NotNull
    private String content;

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
