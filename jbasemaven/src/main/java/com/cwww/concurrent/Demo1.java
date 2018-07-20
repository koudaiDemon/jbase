package com.cwww.concurrent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/15  14:45
 */
public class Demo1 {

    public static void main(String[] args) throws Exception {
        final int thread_count = 100;

        Map<String,String> map = new ConcurrentHashMap<>(10);
//        Map<String,String> map = new HashMap<>();
//        Map<String,String> map = new Hashtable<>(10);

//        List<Thread> list = new ArrayList<>();
        for (int i = 0 ; i < thread_count ; i++) {
            Thread thread = new Thread(() -> {
                if (map.size() < 50) {
                    map.put(Thread.currentThread().getName()+"key",Thread.currentThread().toString()+"value");
                }
            });
//            list.add(thread);
            thread.start();
        }

        Thread.sleep(3000);


        map.forEach((k,v)-> System.out.println(k+":"+v));
        System.out.println(map.size()+":"+map.keySet().size());
    }


}
