package com.cwww.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cWww
 * @Title QueueDemo
 * @Description 用两个线程模拟事件处理
 * @date: 2019/5/16  16:15
 */
public class QueueDemo {

    public static void main(String[] args) {

        int total = 10000;
        AtomicInteger count = new AtomicInteger(0);
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        new Thread(()->{
            while (count.get() < total) {
                System.out.println("每500ms插入一条数据");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final int andIncrement = count.getAndIncrement();
                queue.add("number"+andIncrement);
            }
        }).start();

        new Thread(()->{
            try {
                System.out.println("处理线程先睡眠两分钟");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String poll = queue.poll();
            while (poll != null) {
                try {
                    System.out.println("每1000ms处理一条数据"+poll);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                poll = queue.poll();
            }
        }).start();

        queue.poll();

    }

}
