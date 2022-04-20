package com.cwww.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/16  11:00
 */
public class ListDemo {

    public static void main(String[] args) {

//        List<String> list = Collections.singletonList("hello");
//
//
//        list = Collections.unmodifiableList(list);
//
//        list = new ArrayList<>(list);
//        list.add("hello");
//        System.out.println(list);

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        Iterator<String> iterator = list.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }

}
