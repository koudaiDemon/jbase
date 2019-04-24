package com.something.java8;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cWww
 * @Title Demo
 * @Description
 * @date: 2019/1/9  16:42
 */
public class Demo {

    public static void main(String[] args) {

//        final List<Integer> collect = Collections.<String>emptyList().stream().map(e -> e.length()).collect(Collectors.toList());

//        System.out.println(collect);

        System.out.println(StringUtils.join(Arrays.asList("aaa","bbb"),";"));


    }

}
