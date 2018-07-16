package com.cwww.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/3  14:33
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws Exception {

        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10);

        for (int i = 0 ; i < 10 ; i++) {
            new Thread(()->{
                try {
                    blockingQueue.put("abc");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        new Thread(()->{
            try {
//                blockingQueue.put("abc");
                System.out.println(blockingQueue.remainingCapacity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();



//        blockingQueue.put("abc");
//        System.out.println(blockingQueue.remainingCapacity());
//        System.out.println(blockingQueue.peek());
//        String str = blockingQueue.take();
//        System.out.println(str);
    }

}
