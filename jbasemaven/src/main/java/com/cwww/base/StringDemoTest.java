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

    public static void main(String[] args) {

        String temp = "aaaa:%d:%d";
//        String temp1 = "aaaa";

        long current = System.currentTimeMillis();

        for (int i = 0 ; i < 1000000; i++) {
            String str = String.format(temp, i, i);
//            String str = temp1 + i + i;
        }


        log.info("cost:[{}]", System.currentTimeMillis() - current);


    }

}
