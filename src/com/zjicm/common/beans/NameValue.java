package com.zjicm.common.beans;

/**
 * Created by yujing on 2017/4/12.
 */
public class NameValue<K, V> {
    private K name;
    private V value;

    public NameValue(K name, V value) {
        this.name = name;
        this.value = value;
    }

    public K getName() {
        return name;
    }

    public void setName(K name) {
        this.name = name;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
