package com.zjicm.common.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by yujing on 16-8-16.
 */
public class CustomObjectMapper extends ObjectMapper{
    private boolean camelCaseToLowerCaseWithUnderscores = false;
    private String dateFormatPattern;

    public void setCamelCaseToLowerCaseWithUnderscores(boolean camelCaseToLowerCaseWithUnderscores) {
        this.camelCaseToLowerCaseWithUnderscores = camelCaseToLowerCaseWithUnderscores;
    }

    public void setDateFormatPattern(String dateFormatPattern) {
        this.dateFormatPattern = dateFormatPattern;
    }

    public void init() {
        // 排除值为空属性
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 将驼峰转为下划线
        if (camelCaseToLowerCaseWithUnderscores) {
            setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        }
        // 进行日期格式化
        if (StringUtils.isNotEmpty(dateFormatPattern)) {
            DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
            setDateFormat(dateFormat);
        }
    }
}
