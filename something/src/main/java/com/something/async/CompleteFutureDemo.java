package com.something.async;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 测试CompleteFuture
 *
 * @author wei.cai@hand-china.com 2021/9/8
 */
@Slf4j
public class CompleteFutureDemo {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        String[] results = new String[3];

//        test1(results);
        test2(results);

        log.info("spend: {} ms", (System.currentTimeMillis() - start));
        log.info("results 0:{}", results[0]);
        log.info("results 1:{}", results[1]);
        log.info("results 2:{}", results[2]);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void test2(String[] results) {
        try {
            CompletableFuture.allOf(CompletableFuture.supplyAsync(() -> {
                results[0] = task1();
                return SUCCESS;
            }), CompletableFuture.supplyAsync(() -> {
                results[1] = task2();
                return SUCCESS;
            }), CompletableFuture.supplyAsync(() -> {
                results[2] = task3();
                return SUCCESS;
            })).get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void test1(String[] results) {
        results[0] = task1();
        results[1] = task2();
        results[2] = task3();
    }

    public static String task1() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        log.info("task1=====>");
        return "task1";
    }

    public static String task2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        log.info("task2=====>");
        return "task2";
    }

    public static String task3() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        log.info("task3=====>");
        return "task3";
    }

}
