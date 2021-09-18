package com.something.thread;

/**
 * 进行打断
 *
 * @author wei.cai@hand-china.com 2021/8/31
 */
public class Interrupt {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            int time = 0;
            while (true) {
                time++;

                if (Thread.currentThread().isInterrupted()) {
                    break;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("test:" + time);
            }
        });

        thread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        thread.interrupt();
    }

}
