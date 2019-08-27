package com.something.cache.impl;

import com.something.cache.Dog;
import com.something.cache.EhcacheService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/30  11:44
 */
@Service
public class EhcacheServiceImpl implements EhcacheService {

    @Cacheable(value="HelloWorldCache", key="#param")
    @Override
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }

    @Cacheable(value="HelloWorldCache", key="#key")
    @Override
    public String getDataFromDB(String key) {
        System.out.println("从数据库中获取数据...");
        return key + ":" + String.valueOf(Math.round(Math.random()*1000000));
    }

    @CacheEvict(value="HelloWorldCache", key="#key")
    @Override
    public void removeDataAtDB(String key) {
        System.out.println("从数据库中删除数据");
    }

    @CachePut(value="HelloWorldCache", key="#key")
    @Override
    public String refreshData(String key) {
        System.out.println("模拟从数据库中加载数据");
        return key + "::" + String.valueOf(Math.round(Math.random()*1000000));
    }

    @Override
    public Dog findById(String dogId) {
        return null;
    }

    @Override
    public boolean isReserved(String dogId) {
        return false;
    }

    @Override
    public void removeDog(String dogId) {

    }

    @Override
    public void removeAllDog() {

    }
}
