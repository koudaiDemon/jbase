package com.cwww.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/10  13:45
 */
public class SetDemo {

    public static void main(String[] args) {
        final Set<String> set = new HashSet<>();
        set.add("123");
        set.add("123");
        System.out.println(set);
    }

}
