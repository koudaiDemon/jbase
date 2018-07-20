package com.something.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/4  10:00
 */
public class TestSale {

    public static void main(String[] args) {

        for (int i = 0 ; i < 1000 ; i++) {

            new Thread(() -> {
                Jedis jedis = new Jedis("127.0.0.1",6379);
                jedis.watch("mykey");//监视mykey,主要是解决value<10这个地方的并发问题

                String valueStr = jedis.get("mykey");

                Integer value = Integer.parseInt(valueStr);

                if (value < 10) {//有可能几个线程获得的值都是9，进入if判断后，value变为10，如果不监视的话就会超卖。

                    Transaction tx = jedis.multi();

                    tx.incr("mykey");

                    List<Object> result = tx.exec();//事物执行后，不管是执行成功还是失败，watch都会放弃对mykey的监控。如果

                    //mykey的值被修改，事物将不会执行，也就是从开启事物到执行事物

                    //中间的代码都不会执行；如果mykey的执行没有被修改，那么事物中所有的代码

                    //会原子性执行。
//                    int j = 1;
                    if(result!=null){

                        System.out.println("商品抢购成功！");

                        jedis.sadd("member","username:"+Thread.currentThread().getName());

                    }else{

                        System.out.println("商品抢购失败！");

                    }
                }else{

                    System.out.println("很抱歉商品已被抢完！");

                }
                jedis.unwatch();
            }).start();
        }
    }

}
