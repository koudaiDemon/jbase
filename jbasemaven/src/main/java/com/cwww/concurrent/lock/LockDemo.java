package com.cwww.concurrent.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/3  15:32
 */
public class LockDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(LockDemo.class);

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();
    private String msg;

    public void lockReadingRegistry() {
        this.readLock.lock();
    }

    public void unlockReadingRegistry() {
        this.readLock.unlock();
    }

    public void lockWritingRegistry() {
        this.writeLock.lock();
    }

    public void unlockWritingRegistry() {
        this.writeLock.unlock();
    }

    public boolean isLockedForReading() {
        return this.readWriteLock.getReadLockCount() > 0;
    }

    public boolean isLockedForWriting() {
        return this.readWriteLock.isWriteLocked();
    }

    public void write(String msg){
        this.msg = msg;
        LOGGER.info(Thread.currentThread().getName()+" write: "+msg);
    }

    public String read(){
        LOGGER.info(Thread.currentThread().getName()+" read: "+this.msg);
        return this.msg;
    }


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

    public void test1(){
        //        final LockDemo lockDemo = new LockDemo();

        final Map<String,String> map = new ConcurrentHashMap<>(3);
        final String msg = "msg";

//        LOGGER.info("hello,world");

        boolean[] read = new boolean[]{true,false,true,true,false,false,true,false};



        for (final boolean r : read) {
            new Thread(()-> {
                LOGGER.info(Thread.currentThread().getName()+"start"+r);
                if (r) {
                    final String s = map.get(msg);
                    LOGGER.info(Thread.currentThread().getName()+"msg:"+s);
                } else {
                    int seconds = (int)(Math.random()*10 + 1);
                    map.put(msg,Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(seconds);
                    } catch (InterruptedException e) {
                        LOGGER.error("InterruptedException",e);
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) {



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
