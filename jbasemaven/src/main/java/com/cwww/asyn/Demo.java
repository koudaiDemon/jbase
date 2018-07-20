package com.cwww.asyn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author cWww
 * @Title
 * @Description 同步以及异步的一个小的demo(使用线程完成也可以使用数据库中间件完成)
 * @date: 2018/7/3  11:26
 */
public class Demo {

    public boolean test1(){
        System.out.println("发邮件");
        return false;
    }

    public Future test2(){
//        Callable<String> callable = () -> "";
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future future = executorService.submit(() ->
            {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
                System.out.println("存数据");
            }
        );
        return future;
    }

    public static void asyn(){
        Demo demo = new Demo();

        Future future = demo.test2();
        if (null != future) {
            demo.test1();
        }
    }

    public static void syn() throws Exception{
        Demo demo = new Demo();

        Future future = demo.test2();
        if (null != future) {
            future.get();
            demo.test1();
        }
    }

    public static void main(String[] args) throws Exception {

        //异步,返回一个信号用于告诉收到了,但是对于数据的处理是异步的
        asyn();
        //同步,只有当数据处理完成以后，才执行下一个操作
        syn();

    }

}
