package com.cwww.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.StringJoiner;

/**
 * @author cWww
 * @Title StringDemo
 * @Description StringDemo
 * @date: 2019/11/9  14:26
 */
@Slf4j
public class StringDemoTest {

    @Test
    public void testStringJoiner() {
        StringJoiner stringJoiner = new StringJoiner(";","[","]");
        stringJoiner.add("abc");
        stringJoiner.add("bcd");
        stringJoiner.setEmptyValue("");

        log.info("str:[{}]", stringJoiner.toString());


    }

}
