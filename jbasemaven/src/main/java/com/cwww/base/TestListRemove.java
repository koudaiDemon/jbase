package com.cwww.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Description
 *
 * @author wei.cai@hand-china.com
 * @date 2022/7/1
 */
public class TestListRemove {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("AAAAA");
        list.add("BBBBB");
        list.add("CCCCC");
        list.add("DDDDD");
        list.add("EEEEE");
        list.add("AAAAA");
        list.add("AAAAA");

        final Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            final String next = iterator.next();
            System.out.println("删除" + next);
            list.remove(next);
        }
    }
}
