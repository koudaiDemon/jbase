package com.something.bigdemo.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cWww
 * @Title
 * @Description 缓存的管理
 * @date: 2018/9/3  10:39
 */
public class Manager {
    private static final Logger LOGGER = LoggerFactory.getLogger(Manager.class);

    /**
     * 创建缓存器
     */
    private CacheManager cacheManager;
    private Cache cache;

    public Manager(String cacheName){
        cacheManager = CacheManager.create("something/src/main/resources/ehcache.xml");
        cache = cacheManager.getCache(cacheName);
    }

    public Object getCacheObj(Object key){
        final Element element = cache.get(key);
        return element == null?null:element.getObjectValue();
    }

    public boolean setCacheObj(Object key,Object value){
        final Element element = new Element(key,value);
        try {
            cache.put(element);
            LOGGER.info("cache,{},size,{}",cache.get(key),cache.getSize());
        } catch (Exception e) {
            //todo 针对不同的错误进行不同的处理
            return false;
        }
        return true;
    }

    public boolean removeCacheObj(Object key){
        try {
            LOGGER.info("cache,{},size,{}",cache.get(key),cache.getSize());
            cache.remove(key);
        } catch (Exception e) {
            //todo 针对不同的错误进行不同的处理
            return false;
        }
        return true;
    }

}
