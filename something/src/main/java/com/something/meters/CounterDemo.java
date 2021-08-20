package com.something.meters;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author wei.cai@hand-china.com
 * @Title CounterDemo
 * @Description 测试
 * @date: 2019/9/24  11:04
 */
@Slf4j
public class CounterDemo {


    public static void main(String[] args) throws Exception {
        final MetricRegistry metrics = new MetricRegistry();

        final ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
        final Counter counter = metrics.counter(MetricRegistry.name(CounterDemo.class, "hits", "count"));
        final Random random = new Random();
        while (true) {
            if (0 == random.nextInt(2)) {
                counter.inc();
            }
            Thread.sleep(500);
        }

    }

}
