package com.cwww.exception;

/**
 * @author cWww
 * @Title
 * @Description try catch的执行顺序
 * @date: 2018/6/1  11:07
 */
public class TryCatch {


    public static void test(){
        try {
            StringBuilder sb = null;
            StringBuffer sb1 = null;
            System.out.println("hhhhhh");
//            Math;
//            System.out.println(1/0);
            return;
        } catch (Exception e){
            System.out.println("出错了");
        } finally {
            System.out.println("finally");
        }
    }

    public static String demo1() {
        try {
            return demo();
        } finally {
            System.out.println("hello");
        }
    }

    public static String demo(){
        final String demo = "demo";
        System.out.println(demo);
        return demo;
    }

    public static void main(String[] args) {

        System.out.println("====="+demo1());
    }

}
