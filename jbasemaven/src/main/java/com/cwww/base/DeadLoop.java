package com.cwww.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2021/1/13
 */
public class DeadLoop {

    public static void main(String[] args) {


        final ExecutorService executorService = Executors.newFixedThreadPool(1);

        for (int i = 0 ; i < 1 ; i ++) {

            executorService.submit(()->{
                int num = 0;
                while (true) {
//                    System.out.println(Thread.currentThread().getName() + "  i: " + num);
                    num++;
                }
            });
        }



    }

}
