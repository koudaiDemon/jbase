package com.something.redis;

import redis.clients.jedis.Jedis;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/4  13:41
 */
public class RedisUtil {

    private static Jedis jedis;

    private static RedisUtil redisUtil;


    private RedisUtil(){
        jedis = new Jedis("localhost",6379);
    }

    public static RedisUtil getInstance(){
        if (null == redisUtil) {
            redisUtil = new RedisUtil();
        }
        return redisUtil;
    }

    public Jedis getJedis(){
        return jedis;
    }

    public static void returnResource(Jedis jedis){
        if(null != jedis){
            jedis.close();
        }
    }

}
