package com.something.json;

import com.alibaba.fastjson.JSON;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/12  11:42
 */
public class Demo {

    public static void main(String[] args) {

//        JSON.parseObject("");

        String str = "demo@example.com";
        System.out.println((char)64);
        System.out.println((char)46);
        String lastPart = str.substring(str.indexOf(64));
        int lastDotIndex = lastPart.lastIndexOf(46);

        System.out.println(lastDotIndex);

    }

}
