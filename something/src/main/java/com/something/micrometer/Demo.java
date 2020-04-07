package com.something.micrometer;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.cache.GuavaCacheMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingRegistryConfig;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cWww
 * @Title Demo
 * @Description 演示
 * @date: 2019/9/24  14:03
 */
@Slf4j
public class Demo {
    private static final int CACHE_SIZE = 10000;

    private static final Cache<String, Integer> STRING_INTEGER_CACHE = CacheBuilder.newBuilder()
            .maximumSize(CACHE_SIZE)
//            .recordStats() // required
            .build();

    public static void main(String[] args) {
        final Map<String, String> properties = new HashMap<>(10);
        final LoggingRegistryConfig loggingRegistryConfig = properties::get;
        properties.put(loggingRegistryConfig.prefix() + ".step", "PT10S");
        final MeterRegistry registry = new LoggingMeterRegistry(loggingRegistryConfig, Clock.SYSTEM);

        new JvmMemoryMetrics().bindTo(registry);

        while (true) {

        }

    }


}
