package com.cwww.base;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/6/18
 */
@Slf4j
public class Demo5 {

    public static void main(String[] args) {

        final List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(0);
        list.add(2);


        Comparator.reverseOrder();

        list.sort(Comparator.reverseOrder());


        log.info("list:[{}]", list);

    }

}

