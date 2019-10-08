package com.something.meters;

import com.codahale.metrics.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author cWww
 * @Title TimerDemo
 * @Description 测试
 * @date: 2019/9/24  11:40
 */
@Slf4j
public class TimerDemo {

    public static void main(String[] args) throws Exception {
        final MetricRegistry metrics = new MetricRegistry();

        final ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);

        final Timer timer = metrics.timer(MetricRegistry.name(TimerDemo.class, "get-latency"));
        final Random random = new Random();
        while (true) {
            timer.update(random.nextInt(1000), TimeUnit.SECONDS);
            Thread.sleep(1000);
        }
    }

}
