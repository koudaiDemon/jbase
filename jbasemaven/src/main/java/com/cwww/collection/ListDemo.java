package com.cwww.collection;

import com.sun.javafx.UnmodifiableArrayList;

import java.util.ArrayList;
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


        list = Collections.unmodifiableList(list);

        list = new ArrayList<>(list);
        list.add("hello");
        System.out.println(list);

    }

}
