package com.cwww.nio;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

/**
 * 测试零拷贝
 *
 * @author wei.cai@hand-china.com 2022/4/20
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(time = 3)
@Measurement(iterations = 10, time = 5)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Slf4j
public class ZeroCopyDemo {

    /**
     * 通过mmap方式
     */
    @Benchmark
    public static void mmap() {
        try (FileChannel readChannel = FileChannel.open(Paths.get("/Users/cWww/Documents/IdeaProject/jbase/jbasemaven/src/main/resources/jay.txt"), StandardOpenOption.READ);
             FileChannel writeChannel = FileChannel.open(Paths.get("/Users/cWww/Documents/IdeaProject/jbase/jbasemaven/src/main/resources/siting.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            long size = readChannel.size() > 1024 * 1024 ? 1024 * 1024 : readChannel.size();
            MappedByteBuffer data = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);
            //数据传输
            writeChannel.write(data);
        } catch (Exception e) {
            log.error("mmap error", e);
        }
    }

    /**
     * 由于writeChannel不是socket,体现不出来sendFile的优势
     */
    @Benchmark
    public static void sendFile() {
        try (FileChannel readChannel = FileChannel.open(Paths.get("/Users/cWww/Documents/IdeaProject/jbase/jbasemaven/src/main/resources/jay.txt"), StandardOpenOption.READ);
             FileChannel writeChannel = FileChannel.open(Paths.get("/Users/cWww/Documents/IdeaProject/jbase/jbasemaven/src/main/resources/siting.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);) {

            long len = readChannel.size();
            long position = readChannel.position();

            //数据传输
            readChannel.transferTo(position, len, writeChannel);
        } catch (Exception e) {
            log.error("sendFile error", e);
        }
    }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(ZeroCopyDemo.class.getSimpleName())
                .forks(1)
                .jvmArgsAppend()
                .jvmArgs("-Xmx50m",
                        "-Xms50m",
                        "-Xbatch")
                .build();
        new Runner(opt).run();

    }

}
