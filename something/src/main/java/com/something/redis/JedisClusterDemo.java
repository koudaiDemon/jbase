package com.something.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试JedisCluster+多线程
 *
 * @author wei.cai@hand-china.com 2022/6/7
 */
@Slf4j
public class JedisClusterDemo {

    public static void main(String[] args) throws Exception {

        Set<HostAndPort> hostAndPortSet = new HashSet<>();

        hostAndPortSet.add(new HostAndPort("127.0.0.1",7010));
//        hostAndPortSet.add(new HostAndPort("127.0.0.1",7001));
//        hostAndPortSet.add(new HostAndPort("127.0.0.1",7002));
//        hostAndPortSet.add(new HostAndPort("127.0.0.1",7003));
//        hostAndPortSet.add(new HostAndPort("127.0.0.1",7004));
//        hostAndPortSet.add(new HostAndPort("127.0.0.1",7005));

        JedisCluster newJedisCluster = null;
        try {
            newJedisCluster = new JedisCluster(hostAndPortSet);
        } catch (Exception e) {
            log.error("new jedisCluster Exp", e);
        }

        final JedisCluster jedisCluster = newJedisCluster;

        final ExecutorService executorService = executorService();

        for (int i = 0 ; i < 100 ;i++) {
            try {
                executorService.submit(() -> {
                    try {
                        final String aa = jedisCluster.get("aa");
                        log.info("get aa:[{}]", aa);

                    } catch (Exception e) {
                        log.error("get aa exp", e);
                    }

//                    jedisCluster.close();
                });
            } catch (Exception e) {
                log.error("exp:[{}]", i, e);
            }
        }

        log.info("end !!!");
    }

    private static ExecutorService executorService() {
        return new ThreadPoolExecutor(100,
                200,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                new DefaultThreadFactory(),
                (r, executor) -> {

                    if (executor.isShutdown()) {
                        throw new RejectedExecutionException("Task " + r.toString() +
                                " rejected from " +
                                executor);
                    }

                    final BlockingQueue<Runnable> queue = executor.getQueue();

                    try {
                        if (!queue.offer(r, 20, TimeUnit.MILLISECONDS)) {
                            throw new RejectedExecutionException("Task " + r.toString() +
                                    " rejected from " +
                                    executor);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                });
    }


    private static class DefaultThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "Jedis-Cluster-Demo";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }


}
