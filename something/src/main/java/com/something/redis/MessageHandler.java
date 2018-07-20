package com.something.redis;

import redis.clients.jedis.JedisPubSub;

/**
 * @author cWww
 * @Title
 * @Description redis中消费者的主要处理类，对于不同的处理，应该有不同的处理类
 * @date: 2018/5/22  14:48
 */
public class MessageHandler extends JedisPubSub {

    /**
     * channel频道接收到新消息后，执行的逻辑
     */
    @Override
    public void onMessage(String channel, String message) {
        // 执行逻辑
        System.out.println(channel + "频道发来消息：" + message);
        // 如果消息为 close channel， 则取消此频道的订阅
        if("close channel".equals(message)){
            this.unsubscribe(channel);
        }
    }

    /**
     * channel频道有新的订阅者时执行的逻辑
     * @param channel
     * @param subscribedChannels
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(channel + "频道新增了"+ subscribedChannels +"个订阅者");
    }

    /**
     *  channel频道有订阅者退订时执行的逻辑
     * @param channel
     * @param subscribedChannels
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(channel + "频道退订成功");
    }
}
