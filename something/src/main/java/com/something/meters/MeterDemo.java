package com.something.meters;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author wei.cai@hand-china.com
 * @Title MeterDemo
 * @Description 测试
 * @date: 2019/9/24  11:04
 */
@Slf4j
public class MeterDemo {

    public static void main(String[] args) throws Exception {
        final MetricRegistry metrics = new MetricRegistry();
        final Meter requests = metrics.meter("requests");

        final ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);

        while (true) {
            requests.mark();
            Thread.sleep(500);
        }

    }

}
