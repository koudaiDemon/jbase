package com.something.redis;

import redis.clients.jedis.Jedis;

/**
 * @author cWww
 * @Title
 * @Description redis customer消费者
 * @date: 2018/5/22  14:47
 */
public class Customer {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        MessageHandler handler = new MessageHandler();
        jedis.subscribe(handler, "channel1");
    }

}
