package com.something.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/8  13:01
 */
public class Demo1 {

    public synchronized void hello(){
        System.out.println("hello,world");
    }

    public static void main(String[] args) {

        Demo1 demo1 = new Demo1();
        Map<String,String> map = new HashMap<>();
//        Map<String,String> map = new Hashtable<>();
//        Map<String,String> map = new ConcurrentHashMap<>();


        for (int i = 0 ; i < 10 ; i++) {
            map.put("hello"+i,"world"+i);
        }

        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            demo1.hello();

            map.forEach((key,value)->{
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName()+"  "+key+";"+value);
            });
        }).start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
//            demo1.hello();
            map.forEach((key,value)->{
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName()+"  "+key+";"+value);
                map.put(key,value+Thread.currentThread().getName());
            });
        }).start();

    }

}
