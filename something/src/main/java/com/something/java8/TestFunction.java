package com.something.java8;

import java.util.function.Function;

/**
 * @author cWww
 * @Title
 * @Description java8中Function的demo
 * @date: 2018/5/29  9:49
 */
public class TestFunction {

    public static void main(String[] args) {

        Function<Integer,String> function = (e) -> { e = e + 5; return e + "2";};
        Function<String,Integer> function1 = (e) -> Integer.valueOf(e);
        Runnable runnable = () -> System.out.println("赵翔有点蠢!!");

        new Thread(runnable).start();

        new Thread(()-> System.out.println("")).start();

        System.out.println(function.compose(function1).apply("1"));
        System.out.println(function.andThen(function1).apply(1));
    }

}
