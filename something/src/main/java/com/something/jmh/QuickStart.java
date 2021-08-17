package com.something.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 基于JMH的基准测试,第一次试验
 *
 * @author cWww
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
//@Threads(16)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class QuickStart {

    public static final int COUNT = 1000;

//    @Benchmark
    public String testStringAdd() {
        String a = "";
        for (int i = 0; i < COUNT; i++) {
            a += i;
        }
        return a;
    }

//    @Benchmark
    public String testStringBuilderAdd() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < COUNT; i++) {
            sb.append(i);
        }
        return sb.toString();
    }

    @Benchmark
    public String testForEach() {
        final String[] list = getList();

        final StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
        }

        return sb.toString();
    }

    @Benchmark
    public String testStream() {
        final String[] list = getList();

        final StringBuilder sb = new StringBuilder();

        Arrays.stream(list).forEach(sb::append);


        return sb.toString();
    }

    public String[] getList() {
        final String[] list = new String[COUNT];

        for (int i = 0 ; i < COUNT ; i++) {
            list[i] = "" + i;
        }
        return list;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(QuickStart.class.getSimpleName())
                .forks(1)
//                "-XX:+UnlockDiagnosticVMOptions",
//                "-XX:+LogCompilation",
//                "-XX:+TraceClassLoading",
//                "-XX:+PrintAssembly",
//                "-XX:+PrintCompilation",
//                .jvmArgs("-Xbatch")
                .build();
        new Runner(opt).run();
    }

}