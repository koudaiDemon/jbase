package com.something.test;

import com.alibaba.fastjson.JSON;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/3/21  15:39
 */
public class TestJsonAnnotation {

    public static void main(String[] args) {

        System.out.println(JSON.toJSONString(new User("sadasd","adsada")));

    }

}
