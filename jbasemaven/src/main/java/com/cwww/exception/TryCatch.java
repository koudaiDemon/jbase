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

    public static void main(String[] args) {


        try {

            int i = 0;

            if (i > 1) {
                System.out.println("数据:"+i);
            } else {
                throw new RuntimeException("测试异常");
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
