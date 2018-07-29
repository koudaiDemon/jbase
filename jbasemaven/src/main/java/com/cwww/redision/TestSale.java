package com.cwww.redision;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author cWww
 * @Title
 * @Description 连接池
 * @date: 2018/6/4  11:37
 */
public class TestSale {

    public static void main(String[] args) {

        JedisPool jedisPool = new JedisPool("localhost");
        System.out.println(jedisPool);
    }

}
