package com.something.thread;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/15  10:44
 */
public class Demo2 {

    private volatile int i;

    public Demo2(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void test(){
        i++;
    }

    public static void main(String[] args) {

        Demo2 demo2 = new Demo2(1);
        for (int i = 0 ; i < 100 ;i++) {
            new MyThread(demo2).start();
        }
        System.out.println(demo2.getI());

    }

    public static class MyThread extends Thread{

        private Demo2 demo2;

        public MyThread(Demo2 demo2){
            this.demo2 = demo2;
        }

        public Demo2 getDemo2() {
            return demo2;
        }

        public void setDemo2(Demo2 demo2) {
            this.demo2 = demo2;
        }

        @Override
        public void run() {
            demo2.test();
            System.out.println(Thread.currentThread().getName()+"====="+demo2.getI());
        }
    }

}
