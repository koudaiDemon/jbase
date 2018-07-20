package com.cwww.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/4  13:41
 */
public class RedisUtil {

    private static JedisPool jedisPool;

    private static RedisUtil redisUtil;


    private RedisUtil(){
        jedisPool = new JedisPool("localhost",6379);
    }

    public static RedisUtil getInstance(){
        if (null == redisUtil) {
            redisUtil = new RedisUtil();
        }
        return redisUtil;
    }

    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    public static void returnResource(Jedis jedis){
        if(null != jedis){
            jedis.close();
        }
    }

}
