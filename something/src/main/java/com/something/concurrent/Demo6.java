package com.something.concurrent;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/11  9:23
 */
public class Demo6 {

    public static void main(String[] args) {
        //        Map<String,String> map = new HashMap<>();
        Map<String,String> map = new Hashtable<>();
        //        Map<String,String> map = new ConcurrentHashMap<>();
        ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<>();
        threadLocal.set(map);
//        Thread t1 = new Thread();
        for (int i = 0 ; i < 100 ; i++){
            new MyThread(threadLocal).start();
        }
    }

    public static class MyThread extends Thread{

        private Map<String,String> map;

        public MyThread(){

        }

        public MyThread(ThreadLocal<Map<String,String>> threadLocal){
            this.map = threadLocal.get();
        }

        @Override
        public  void run() {
            System.out.println(Thread.currentThread().getName()+"开始了 map,size"+map.size());
            synchronized (map){
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

}
