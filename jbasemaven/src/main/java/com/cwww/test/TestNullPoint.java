package com.cwww.test;

import org.apache.commons.lang.StringUtils;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/9  12:13
 */
public class TestNullPoint {

    public static void main(String[] args) {

        String str = null;

        System.out.println(null == str || str.equals(""));

        System.out.println(str.equals("") || null == str);

        System.out.println(StringUtils.isEmpty(str));

    }

}
