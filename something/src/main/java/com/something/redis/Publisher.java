package com.something.redis;

import redis.clients.jedis.Jedis;

/**
 * @author cWww
 * @Title
 * @Description redis publisher 生产者
 * @date: 2018/5/22  14:46
 */
public class Publisher {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 向“channel1”的频道发送消息, 返回订阅者的数量
//        Long publishCount = jedis.publish("channel1", new Date() + ": hello redis channel1");
        Long publishCount = jedis.publish("channel1","你好呀");
        System.out.println("发送成功，该频道有" +publishCount + "个订阅者");
    }

}
