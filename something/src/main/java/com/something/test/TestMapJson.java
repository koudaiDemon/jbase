package com.something.test;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/3/19  13:58
 */
public class TestMapJson {

    public static void main(String[] args) throws Exception {
        final Map<String,Integer> map = new LinkedHashMap<>(10);
        for (int i = 0; i < 10; i++) {
            if(i%2 == 0){
                map.put("str"+i,i);
            }
        }
        System.out.println(JSON.toJSONString(map));

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    while(true){
//                        Thread.sleep(1000);
//                        System.out.println("hello");
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        };
//        Thread thread = new Thread(runnable);
//        thread.start();
//        Thread.sleep(5000);
//        thread.stop();
//        double d = 1/3*1.0;
//        System.out.println(d);

//        String str = "abcdefghi";

//        System.out.println(str.substring(3));
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(TestMapJson.class.getResourceAsStream("/a.txt"));
//        byte[] bytes = new byte[1024];
//        bufferedInputStream.read(bytes);
//        System.out.println(new String(bytes));
//        bufferedInputStream.close();
        Optional<String> optional = Optional.ofNullable(null);
        if(optional.isPresent()){
            System.out.println(optional.get());
        }
    }

}
