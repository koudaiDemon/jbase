package com.cwww.redis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cwww.redis.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 乐观锁实现秒杀系统
 *
 */
public class OptimisticLockTest {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        initProduct();
        initClient();
        printResult();
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("程序运行时间 ： " + (int)time + "ms");
    }

    /**
     * 初始化商品
     * @date 2017-10-17
     */
    public static void initProduct() {
        int prdNum = 100;//商品个数
        String key = "prdNum";
        String clientList = "clientList";//抢购到商品的顾客列表
        Jedis jedis = RedisUtil.getInstance().getJedis();
        if (jedis.exists(key)) {
            jedis.del(key);
        }
        if (jedis.exists(clientList)) {
            jedis.del(clientList);
        }
        jedis.set(key, String.valueOf(prdNum));//初始化商品
        RedisUtil.returnResource(jedis);
    }

    /**
     * 顾客抢购商品（秒杀操作）
     * @date 2017-10-17
     */
    public static void initClient() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        int clientNum = 10000;//模拟顾客数目
        for (int i = 0; i < clientNum; i++) {
            cachedThreadPool.execute(new ClientThread(i));//启动与顾客数目相等的消费者线程
        }
        cachedThreadPool.shutdown();//关闭线程池
        while (true) {
            if (cachedThreadPool.isTerminated()) {
                System.out.println("所有的消费者线程均结束了");
                break;
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    /**
     * 打印抢购结果
     * @date 2017-10-17
     */
    public static void printResult() {
        Jedis jedis = RedisUtil.getInstance().getJedis();
        Set<String> set = jedis.smembers("clientList");
        int i = 1;
        for (String value : set) {
            System.out.println("第" + i++ + "个抢到商品，"+value + " ");
        }
        RedisUtil.returnResource(jedis);
    }


    /**
     * 内部类：模拟消费者线程
     */
    static class ClientThread implements Runnable{

        Jedis jedis = null;
        String key = "prdNum";//商品主键
        String clientList = "clientList";//抢购到商品的顾客列表主键
        String clientName;

        public ClientThread(int num){
            clientName = "编号=" + num;
        }

        //      1.multi，开启Redis的事务，置客户端为事务态。
//      2.exec，提交事务，执行从multi到此命令前的命令队列，置客户端为非事务态。
//      3.discard，取消事务，置客户端为非事务态。
//      4.watch,监视键值对，作用是如果事务提交exec时发现监视的键值对发生变化，事务将被取消。
        @Override
        public void run() {
            try {
                Thread.sleep((int)Math.random()*5000);//随机睡眠一下
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while(true){
                System.out.println("顾客：" + clientName + "开始抢购商品");
                jedis = RedisUtil.getInstance().getJedis();
                try {
                    jedis.watch(key);//监视商品键值对，作用时如果事务提交exec时发现监视的键值对发生变化，事务将被取消
                    int prdNum = Integer.parseInt(jedis.get(key));//当前商品个数
                    if (prdNum > 0) {
                        Transaction transaction = jedis.multi();//开启redis事务
//                        ((Jedis) transaction).set(key,String.valueOf(prdNum - 1));//商品数量减一
                        transaction.decr(key);
                        List<Object> result = transaction.exec();//提交事务(乐观锁：提交事务的时候才会去检查key有没有被修改)
                        if (result == null || result.isEmpty()) {
                            System.out.println("很抱歉，顾客:" + clientName + "没有抢到商品");// 可能是watch-key被外部修改，或者是数据操作被驳回
                        }else {
                            jedis.sadd(clientList, clientName);//抢到商品的话记录一下
                            System.out.println("恭喜，顾客:" + clientName + "抢到商品");
                            break;
                        }
                    }else {
                        System.out.println("很抱歉，库存为0，顾客:" + clientName + "没有抢到商品");
                        break;
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }finally{
                    jedis.unwatch();
                    RedisUtil.returnResource(jedis);
                }
            }

        }
    }

}