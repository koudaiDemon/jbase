package com.cwww.concurrent;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/15  14:58
 */
public class Demo1Test {

    @Test
    public void test(){

        Map<String,String> map = new HashMap<>();
        int i = 0;
        while (map.size() <= 50) {
            map.put("key"+i,"value"+i);
            i++;
        }
        System.out.println(map.size());
//        System.out.println(Thread.activeCount());

    }

}