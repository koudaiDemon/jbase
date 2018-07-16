package com.cwww.collection;

import java.util.Collections;
import java.util.List;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/16  11:00
 */
public class ListDemo {

    public static void main(String[] args) {

        List<String> list = Collections.singletonList("hello");

        list.add("hello");
        System.out.println(list);

    }

}
