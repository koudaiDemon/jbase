package com.something.redis;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/5/31  12:03
 */
public class TestLock {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        System.out.println(jedis.set("test","test"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        System.out.println(jedis.set("lock:123456",simpleDateFormat.format(new Date())));
//        System.out.println(jedis.del("lock:123456"));
        jedis.setnx("","");

    }

}
