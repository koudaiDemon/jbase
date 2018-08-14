package com.something.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cWww
 * @Title
 * @Description
 * @date 2018/8/14 下午1:52
 */
public class TestStream02 {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestStream02.class);

    public static void main(String[] args) {

        String str1 = "hello";
        String str2 = "world";

        //流式数据之间的转换
        List<String> collect = Stream.of(str1, str2).flatMap(e -> Arrays.stream(e.split(""))).distinct().collect(Collectors.toList());


        LOGGER.info("字符串,{}{},分割去重以后,{}",str1,str2,collect);

    }

}
