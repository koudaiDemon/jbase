package com.cwww.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/24  10:43
 */
public class Demo4 {

    private static List<String> list = new ArrayList<>();

    public void test(){

        for (int i = 0 ; i < 10 ; i++) {
            new Thread(()->{
                for (int j = 0 ; j < 10 ; j++) {
                    list.add("test--"+Thread.currentThread().getName()+"---"+j);
                }
            }).start();
        }

    }


    public static void main(String[] args) throws Exception {

        new Demo4().test();

        Thread.sleep(1000);
        System.out.println(Demo4.list.size());

    }

}
