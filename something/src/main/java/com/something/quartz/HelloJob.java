package com.something.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cWww
 * @Title HelloJob
 * @Description job
 * @date: 2019/7/24  11:39
 */
@Slf4j
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class HelloJob implements Job {

    private static final AtomicInteger COUNT = new AtomicInteger(0);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("hello count:{},currentTime:{}",COUNT.incrementAndGet(),System.currentTimeMillis());
    }

}
