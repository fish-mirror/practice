package com.zjicm.customkeyvalue.beans;

import org.hibernate.validator.constraints.Length;

/**
 * 自定义键值对参数
 * <p>
 * Created by yujing on 2017/5/13.
 */
public class CustomKeyParams {
    @Length(max = 50)
    private String name;
    @Length(max = 50)
    private String key;
    private String value;
    @Length(max = 50)
    private String remarks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
