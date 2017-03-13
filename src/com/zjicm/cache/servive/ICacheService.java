package com.zjicm.cache.servive;

import java.io.Serializable;

/**
 * Created by yujing on 2017/3/11.
 */
public interface ICacheService {
    <T extends Serializable> T get(String storage, String key);
    boolean set(String storage,String key,Serializable value,int ttl);
    void clear(String storage,String key);
}
