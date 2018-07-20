package com.something.concurrent;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/8  13:58
 */
public class Demo5 {

    private Map<String,String> map;

    public Demo5(){

    }

    public Demo5(Map<String,String> map){
        this.map = map;
    }

    public synchronized void run() {
        System.out.println(Thread.currentThread().getName()+"开始了 map,size"+map.size());
        if (null == map || 0 == map.size()) {
            System.out.println(Thread.currentThread().getName()+"  你好呀,更改map");
            map.put(Thread.currentThread().getName()+"key",Thread.currentThread().getName()+"value");
            System.out.println(Thread.currentThread().getName()+"  还原map");
            map.clear();
        } else {
            System.out.println(Thread.currentThread().getName()+map);
        }
    }

    public static void main(String[] args) {

//        Map<String,String> map = new HashMap<>();
        Map<String,String> map = new Hashtable<>();
//        Map<String,String> map = new ConcurrentHashMap<>();
        Demo5 demo4 = new Demo5(map);
        for (int i = 0 ; i < 100 ; i++){
            new MyThread(demo4).start();
        }

    }

    public static class MyThread extends Thread{

        private Demo5 demo5;

        public MyThread(){

        }

        public MyThread(Demo5 demo5){
            this.demo5 = demo5;
        }

        @Override
        public  void run() {
            demo5.run();
        }
    }

}
