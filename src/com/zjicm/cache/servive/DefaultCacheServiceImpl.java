package com.zjicm.cache.servive;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 默认缓存实现
 *
 * Created by yujing on 2017/3/11.
 */
@Service
public class DefaultCacheServiceImpl implements CacheService {
    public final static Map<String, Map<String, Serializable>> storageMap = new HashMap<>();

    @Override
    public <T extends Serializable> T get(String storage, String key) {
        if (!storageMap.containsKey(storage)) return null;

        Map<String, Serializable> map = storageMap.get(storage);
        if (MapUtils.isEmpty(map)) return null;

        return (T)map.get(key);
    }

    @Override
    public boolean set(String storage, String key, Serializable value, int ttl) {
        if (storageMap.containsKey(storage)) {
            storageMap.get(storage).put(key,value);
        } else {
            Map<String, Serializable> map = new HashMap<>();
            map.put(key, value);
            storageMap.put(storage, map);
        }
        return true;
    }

    @Override
    public void clear(String storage, String key) {
        if (storageMap.containsKey(storage)) {
            storageMap.get(storage).remove(key);
        }
    }
}
