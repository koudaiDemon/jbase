package com.something.thread;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/11  10:46
 */
public class Demo1 {

    private  boolean flag;

    public void test(){
        System.out.println(flag);
        while (!flag) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("=================");
        }
    }

    public void test2(){
        flag = true;
        System.out.println("改变了flag");
    }

    public static void main(String[] args) {
//        for (int i = 0 ; i < 10 ; i++) {
            Demo1 demo1 = new Demo1();
            new Thread(() -> {
                demo1.test();
            }).start();

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                demo1.test2();
            }).start();
//        }

    }

}
