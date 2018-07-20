package com.cwww.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/5  10:25
 */
public class Demo2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo2.class);

    public String test() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return "hello";
    }

    public static void main(String[] args) throws Exception {

        Demo2 demo2 = new Demo2();
//        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("hello,{}" , demo2.test());
//        }
        List<String> list = new ArrayList<>();
//        list.parallelStream().
//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return 0;
//            }
//        });



        LOGGER.info("结束");

    }

}
