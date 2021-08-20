package com.something.meters;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author cWww
 * @Title HistogramDemo
 * @Description 测试
 * @date: 2019/9/24  11:40
 */
@Slf4j
public class HistogramDemo {

    public static void main(String[] args) throws Exception {
        final MetricRegistry metrics = new MetricRegistry();

        final ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);

        //95%
        final Histogram histogram = new Histogram(new ExponentiallyDecayingReservoir());
        metrics.register(MetricRegistry.name(HistogramDemo.class,"request","Histogram"), histogram);

        final Random random = new Random();
        while (true) {
            histogram.update(random.nextInt(10000));
            Thread.sleep(1000);
        }
    }

}
