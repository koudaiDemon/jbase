package com.something.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/14  12:26
 */
public class TestStream {

    public static void main(String[] args) {

        final List<Product> list = new ArrayList<>();

        Product product1 = new Product("spu1",null);
        Product product2 = new Product("spu2",null);

        Product product3 = new Product("sku1",product1);
        Product product4 = new Product("sku2",product1);

        Product product5 = new Product("sku1",product2);
        Product product6 = new Product("sku2",product2);
        list.add(product3);
        list.add(product4);
        list.add(product5);
        list.add(product6);

        List<String> collect = list.stream().map(e -> e.getBaseProduct().getCode()).collect(Collectors.toList());

        List<String> distinctCollect = list.stream().map(e -> e.getBaseProduct().getCode()).distinct().collect(Collectors.toList());

        System.out.println("未去重的spu"+collect);
        System.out.println("去重以后的spu"+distinctCollect);

    }

}
