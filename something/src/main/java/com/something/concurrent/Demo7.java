package com.something.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/11  9:23
 */
public class Demo7 {

    private ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<>();

    public Map<String,String> getMap(){
        if (null != threadLocal && null != threadLocal.get()) {
            return threadLocal.get();
        } else {
            System.out.println("创建hashmap");
            final Map<String,String> map = new HashMap<>();
            threadLocal.set(map);
            return map;
        }
    }

    public void realease(){
        threadLocal.remove();
    }

    public static void main(String[] args) {
        //        Map<String,String> map = new HashMap<>();
//        Map<String,String> map = new Hashtable<>();
        //        Map<String,String> map = new ConcurrentHashMap<>();
//        ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<>();
//        threadLocal.set(map);
//        Thread t1 = new Thread();
        Demo7 demo7 = new Demo7();
        for (int i = 0 ; i < 100 ; i++){
            new MyThread(demo7).start();
        }


    }

    public static class MyThread extends Thread{

        private Demo7 demo7;
        private Map<String,String> map;

        public MyThread(Demo7 demo7){
            this.demo7 = demo7;
        }

        @Override
        public  void run() {
            map = demo7.getMap();
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
    }

}
