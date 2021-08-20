package com.something.java8;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/3/19
 */
@Slf4j
public class ClosureDemo {

    public static void main(String[] args) {

        List<String> array = Arrays.asList("aaa","bbb","aaaa","bbbb");

        final Map<Boolean, List<String>> collect = array.stream()
                .collect(Collectors.partitioningBy(new Predicate<String>(){
                    private final Set<Object> seen = ConcurrentHashMap.newKeySet();
                    @Override
                    public boolean test(String s) {
                        return seen.add(distinctExtractor().apply(s));
                    }
                }));

        log.info("collect:[{}]", collect);
    }

    protected static <T> Function<T, Object> distinctExtractor(){
        return t -> t.toString().length();
    }

    /**
     * 此处为闭包
     * @param <T>
     * @return
     */
    private static <T> Predicate<T> distinctByKey() {
        /*
         *  Java 8 之后可以不指定 final，然而这块语法糖不好吃
         *  利用 Set 储存过程变量
         */
        final Set<Object> seen = ConcurrentHashMap.newKeySet();
        // add 成功则返回 true，在此代表数据无需过滤
        return t -> seen.add(distinctExtractor().apply(t));
    }

}
