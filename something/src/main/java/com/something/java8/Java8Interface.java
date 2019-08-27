package com.something.java8;

/**
 * @author cWww
 * @Title 接口可以实现多继承
 * @Description
 * @date 2018/8/14 下午2:00
 */
public interface Java8Interface {

    /**
     * 常量
     */
    String STRING = "CONTENT";

    void abstractMethod();

    /**
     * 新版
     */
    static void staticMethod(){

    }

    /**
     * Java8早期
     */
    default void defaultMethod(){

    }

}
