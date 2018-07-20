package com.cwww.exception;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/1  11:07
 */
public class TryCatch {



    public static void main(String[] args) {


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

}
