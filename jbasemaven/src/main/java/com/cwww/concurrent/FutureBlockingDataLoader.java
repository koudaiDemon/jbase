package com.cwww.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author cWww
 * @Title 限流模型
 * @Description 限流算法
 * @date: 2019/4/26  13:15
 */
public class FutureBlockingDataLoader extends DataLoader {

    protected void doLoad() {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //  耗时 >= 1s
        runCompletely(executorService.submit(super::loadConfigurations));
        //  耗时 >= 2s
        runCompletely(executorService.submit(super::loadUsers));
        //  耗时 >= 3s
        runCompletely(executorService.submit(super::loadOrders));
        executorService.shutdown();
    } // 总耗时 sum(>= 1s, >= 2s, >= 3s)  >= 6s

    private void runCompletely(Future<?> future) {
        try {
            future.get(); // 阻塞等待结果执行
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        new FutureBlockingDataLoader().load();
    }

}