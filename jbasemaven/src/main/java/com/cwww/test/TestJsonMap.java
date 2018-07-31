package com.cwww.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cWww
 * @Title
 * @Description 测试map转json，值为数字
 * @date: 2018/3/19  13:52
 */
public class TestJsonMap {


    public static void main(String[] args) throws Exception {
        final Map<String,Integer> map = new HashMap<>(10);
        for (int i = 0; i < 10; i++) {
            map.put("str"+i,i);
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        System.out.println(json);
    }


}
