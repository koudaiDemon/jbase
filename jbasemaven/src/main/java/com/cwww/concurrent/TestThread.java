package com.cwww.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author cWww
 * @Title
 * @Description
 * @date 2018/8/8 下午11:20
 */
public class TestThread {

    public static void main(String[] args) throws Exception {

        Thread thread = new Thread(() -> {
//            for (int i = 0; i < 5000; i++) {
////                System.out.println("i:"+i);
////                try {
////                    Thread.sleep(1000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//
//            }
//            while (true) {
//
//            }
            while (true) {
                try {
                    System.out.println("123");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println("强行中断出错");
                    return;
                }
            }
        });
        thread.start();
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }


    public void sleep(long time){
        final long currentTimeMillis = System.currentTimeMillis();
        while (System.currentTimeMillis() - currentTimeMillis >= time) {
            
        }
    }

}
