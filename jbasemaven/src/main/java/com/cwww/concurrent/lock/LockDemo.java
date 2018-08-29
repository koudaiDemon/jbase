package com.cwww.concurrent.lock;

import org.apache.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/3  15:32
 */
public class LockDemo {

    private static final Logger LOGGER = Logger.getLogger(LockDemo.class);

//    private final Lock lock = new ReentrantLock();
//    private final Condition notFull  = lock.newCondition();
//    private final Condition notEmpty = lock.newCondition();
//
//    private final Object[] items = new Object[100];
//    private int putptr, takeptr, count;
//
//    public void put(Object x) throws InterruptedException {
//        lock.lock();
//        try {
//            while (count == items.length)
//            {
//                notFull.await();
//            }
//            items[putptr] = x;
//            if (++putptr == items.length) {
//                putptr = 0;
//            }
//            ++count;
//            notEmpty.signal();
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public Object take() throws InterruptedException {
//        lock.lock();
//        try {
//            while (count == 0)
//                notEmpty.await();
//            Object x = items[takeptr];
//            if (++takeptr == items.length) takeptr = 0;
//            --count;
//            notFull.signal();
//            return x;
//        } finally {
//            lock.unlock();
//        }
//    }

    public static void test(){
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        try {
            LOGGER.info(Thread.currentThread().getName()+"锁住了test()");
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {

//        LOGGER.info("hello,world");

//        new Thread(()-> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            LockDemo.test();
//        }).start();
//        new Thread(()-> LockDemo.test()).start();



    }

}
