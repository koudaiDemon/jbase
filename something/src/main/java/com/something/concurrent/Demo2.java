package com.something.concurrent;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/8  13:19
 */
public class Demo2 {

    private volatile boolean stopping;

    public synchronized void setStopping(boolean stopping){
        this.stopping = stopping;
    }

    public boolean getStopping(){
        return this.stopping;
    }


    public static void main(String[] args) {
//        long time1 = System.currentTimeMillis();
        Demo2 demo2 = new Demo2();
        for (int i = 0 ; i < 100 ; i++){
           new MyThread(demo2).start();
        }
//        long time2 = System.currentTimeMillis();

//        System.out.println("time2 - time1"+(time2-time1));

    }

    public static class MyThread extends Thread{

        private Demo2 demo2;

        public MyThread(){

        }

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
//            super.run();
            System.out.println(Thread.currentThread().getName()+"开始了 stopping"+demo2.getStopping());
            if (!demo2.getStopping()) {
                System.out.println(Thread.currentThread().getName()+"  你好呀,更改stopping");
                demo2.setStopping(true);
                System.out.println(Thread.currentThread().getName()+"  还原stopping");
                demo2.setStopping(false);
            } else {
                System.out.println(Thread.currentThread().getName()+"  没有进来");
            }
        }
    }

}
