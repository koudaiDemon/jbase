package com.something.bigdemo.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/3  10:53
 */
public class DBService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBService.class);

    public static void initCache(Map<String,String> cacheMap){
        LOGGER.info("初始化节点缓存数据");
        cacheMap.put("key1","value1");
        cacheMap.put("key2","value2");
        cacheMap.put("key3","value3");
        cacheMap.put("key4","value4");
    }

    /**
     * 伪代码
     * @param item
     * @return
     */
    public static boolean excute(Item item){
        LOGGER.info("数据库,{},{}",item.getPersistentEnum(),item.toString());
        return true;
    }

}
