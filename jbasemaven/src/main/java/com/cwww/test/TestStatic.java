package com.cwww.test;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/25  17:10
 */
public class TestStatic {

    static {
        System.out.println("初始化1");
    }

    {
        System.out.println("初始化2");
    }

    public static void test(){
        System.out.println("初始化3");
    }

    public static void main(String[] args) {

        //1.直接使用main方法,则会执行static{},(由于是在当前类中)
        try {
            Class.forName("com.cwww.test.TestStatic");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2.使用test方法，会执行test静态方法和static{}
        TestStatic.test();
        //3.只有当类被创建以后{}中的代码才会被执行
        TestStatic testStatic = new TestStatic();

    }

}
