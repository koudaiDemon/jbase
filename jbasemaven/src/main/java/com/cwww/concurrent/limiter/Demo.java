package com.cwww.concurrent.limiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * @author cWww
 * @Title 限流模型
 * @Description 限流算法
 * @date: 2019/4/26  13:15
 */
@Slf4j
public class Demo {

    public void rateLimiter(){
        RateLimiter rateLimiter = RateLimiter.create(5.0);

//        Thread.sleep(1000);
        for (int i = 1 ; i < 10 ; i++) {
            final double acquire = rateLimiter.acquire(i);
            log.info("cutTime:{},acquire:{},waitTime:{}",System.currentTimeMillis(),i,acquire);
        }
    }

    public static void main(String[] args) throws Exception {
//        LinkedList<String> queue = new LinkedList<>();
//        Map<String,String> resource = new HashMap<>(10);

        Semaphore semaphore = new Semaphore(1);
//        ConcurrentLinkedQueue

        new Thread(()->{
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }

        }).start();


    }

}
