package com.cwww.base;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Set;

/**
 * @author cWww
 * @Title 两个main方法互相之间不干扰
 * @Description
 * @date: 2018/9/3  10:14
 */
public class Demo4 {

    public static void main(String[] args) {

//        System.out.println(StaticDemo.demo);
        String str = Arrays.asList("aaa","bbbb","cccc").toString();


        final Set<String> strings = StringUtils.commaDelimitedListToSet(str);

        strings.forEach(System.out::println);

//        System.out.println(strings);

    }

}
