package com.something.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/14  12:26
 */
public class TestStream {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestStream.class);

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

        //复制一个同等list
        final List<Product> list2 = list.subList(0,list.size());

        List<String> collect = list.stream().map(e -> e.getBaseProduct().getCode()).collect(Collectors.toList());

        List<String> distinctCollect = list.stream().map(e -> e.getBaseProduct().getCode()).distinct().collect(Collectors.toList());

        Set<String> set = list.stream().map(e -> e.getBaseProduct().getCode()).collect(Collectors.toSet());

        Map<String, List<Product>> map = list.stream().collect(Collectors.groupingBy(e -> e.getBaseProduct().getCode()));

//        list.stream().flatMap()

        //map与flatmap的区别,map会返回一个指定的类型，而flatmap返回的还是流式数据
        List<Product> sku1 = Stream.of(list, list2).flatMap(e -> e.stream().filter(item -> item.getCode().equalsIgnoreCase("sku1"))).collect(Collectors.toList());



        LOGGER.info("flatMap:{}",sku1);
        LOGGER.info("也可以达到去重,spuSet:{}",set);
        LOGGER.info("按照spu进行Map集合,size:{},map:{}",map.size(),map);
        LOGGER.info("未去重的spu:{}",collect);
        LOGGER.info("去重以后的spu:{}",distinctCollect);

    }

}
