package com.cwww.concurrent.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/4/22  14:16
 */
public class LockDemo2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(LockDemo2.class);

    public static void main(String[] args) throws Exception {
        final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        final Lock readLock = readWriteLock.readLock();
        final Lock writeLock = readWriteLock.writeLock();

        new Thread(()->{
            writeLock.lock();
            System.out.println(Thread.currentThread().getName()+"锁住了！！！！");
            int seconds = (int)(Math.random()*10 + 1);
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException",e);
            } finally {
                writeLock.unlock();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);
        if (readLock.tryLock()) {
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+"锁住了！！！！");
        }

    }

}
