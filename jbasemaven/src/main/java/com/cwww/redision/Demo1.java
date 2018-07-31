package com.cwww.redision;

import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RKeys;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author cWww
 * @Title
 * @Description 分布式锁的使用
 * @date: 2018/5/31  13:48
 */
public class Demo1 {

    private static final String PREFIX = "lock:";

    public static void main(String[] args) throws Exception {

        final Config baseConfig = new Config();
        baseConfig.useSingleServer()
                .setConnectTimeout(30000)
                .setConnectionPoolSize(64)
                .setConnectionMinimumIdleSize(32)
                .setIdleConnectionTimeout(3000)
//                .setPassword("caiwei")
                .setAddress("redis://127.0.0.1:6379");

        RedissonClient client = Redisson.create(baseConfig);

//        client.getLock("");
//        client.
//        final RKeys keys = client.getKeys();
//        final Iterable<String> keys1 = keys.getKeys();
//        keys1.forEach(System.out::println);
//        System.out.println(client.getKeys());
        final String key = "hello";
        final RLock lock = client.getLock(PREFIX+key);
//        RLock[] res = {};
//        final RedissonRedLock redissonRedLock = new RedissonRedLock(lock,lock,lock,lock,lock);
//        redissonRedLock.tryLock();
//        redissonRedLock = new
//        RedissonMultiLock

        System.out.println(lock.isLocked());
        //上锁,5秒后自动删除锁
        lock.lock(5,TimeUnit.SECONDS);
        //上锁,等待2秒,10秒后自动删除锁
//        System.out.println(lock.tryLock(2,10,TimeUnit.SECONDS));
        //是否上锁
        System.out.println(lock.isLocked());
        /*
        lock.unlock();
        System.out.println(lock.isLocked());
        System.out.println();
        Thread.sleep(10000);
        new Thread( ()-> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final RLock lock1 = client.getLock(PREFIX+key);
            System.out.println(lock1.isLocked());} ).start();*/
        //手动释放锁
        lock.unlock();
        //关闭客户端
        client.shutdown();

    }

    public void thread1(String key,RedissonClient client){
        final RLock lock = client.getLock(PREFIX+key);
        System.out.println(lock.isLocked());
        lock.lock(2,TimeUnit.SECONDS);
    }

}
