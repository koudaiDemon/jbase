package com.something.meters;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author wei.cai@hand-china.com
 * @Title GaugeDemo
 * @Description 测试
 * @date: 2019/9/24  11:04
 */
@Slf4j
public class GaugeDemo {

    private static final Queue<String> STRING_QUEUE = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        final MetricRegistry metrics = new MetricRegistry();

        final ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);

        final Gauge<Integer> register = metrics.register(MetricRegistry.name(GaugeDemo.class, "queue", "size"), STRING_QUEUE::size);

        while (true) {
            STRING_QUEUE.add("job - " + System.currentTimeMillis());
            Thread.sleep(500);
        }

    }

}
