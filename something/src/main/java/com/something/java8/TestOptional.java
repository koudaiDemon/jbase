package com.something.java8;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/5/29  10:10
 */
public class TestOptional {

    public static void main(String[] args) {

        String str = "nihao";
        String str1 = null;
        Optional<String> stringOptional = Optional.ofNullable(str);
        //使用ifPresent可以使用lamada表达式
        stringOptional.ifPresent(System.out::println);
        stringOptional.map( string -> string + "世界" ).ifPresent(System.out::println);
        stringOptional.flatMap( string -> Optional.ofNullable(string + "世界") ).ifPresent(System.out::println);
        stringOptional.filter( string -> string.equals("nihao")).ifPresent(System.out::println);
        stringOptional.map(Collections::singletonList).ifPresent(System.out::println);
//        collect(opt, toList());
        Predicate<String> predicate = e -> e.equals("测试");
        System.out.println();
    }

}
