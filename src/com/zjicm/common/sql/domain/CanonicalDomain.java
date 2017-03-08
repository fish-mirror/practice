package com.zjicm.common.sql.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
public interface CanonicalDomain<K> extends Serializable {
    K getId();

    void prepare();

    Date getCreateTime();

    void setCreateTime(Date createTime);

    Date getModifyTime();

    void setModifyTime(Date modifyTime);
}
