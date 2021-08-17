package com.cwww.base;

import lombok.extern.slf4j.Slf4j;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2021/4/12
 */
@Slf4j
public class DeadLock {

    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();


    public void fun1() {
        synchronized (LOCK1) {
            log.info("Thread:[{}],Lock LOCK1", Thread.currentThread().getName());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("", e);
            }
            synchronized (LOCK2) {
                log.info("Thread:[{}],Lock LOCK2", Thread.currentThread().getName());
            }
        }
    }

    public void fun2() {
        synchronized (LOCK2) {
            log.info("Thread:[{}],Lock LOCK2", Thread.currentThread().getName());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.error("", e);
            }

            synchronized (LOCK1) {
                log.info("Thread:[{}],Lock LOCK1", Thread.currentThread().getName());
            }
        }
    }


    public static void main(String[] args) {

        final DeadLock deadLock = new DeadLock();

        new Thread(() -> {
            deadLock.fun1();
        }).start();

        new Thread(() -> {
            deadLock.fun2();
        }).start();

    }

}
