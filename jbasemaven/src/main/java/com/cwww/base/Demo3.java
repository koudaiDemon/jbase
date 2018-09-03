package com.cwww.base;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/3  10:14
 */
public class Demo3 {

    public static void main(String[] args) throws Exception {

        StaticDemo.demo = "world";

        System.out.println(StaticDemo.demo);

        Thread.sleep(5000);

    }

}
