package com.cwww.concurrent.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/4/22  14:16
 */
public class LockDemo3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(LockDemo3.class);
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {

        for (int i = 0 ; i < 10 ; i ++) {
            new Thread(()->{
                int seconds = (int)(Math.random()*10 + 1);
                try {
                    lock.lock();
                    LOGGER.info("Thread:{},locked,times:{}",Thread.currentThread().getName(),seconds);
                    TimeUnit.SECONDS.sleep(seconds);
                } catch (InterruptedException e) {
                    LOGGER.error("InterruptedException",e);
                } finally {
                    lock.unlock();
                }
            }).start();


        }

    }

}
