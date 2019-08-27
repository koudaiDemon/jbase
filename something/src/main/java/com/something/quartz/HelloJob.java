package com.something.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cWww
 * @Title HelloJob
 * @Description job
 * @date: 2019/7/24  11:39
 */
@Slf4j
//@DisallowConcurrentExecution
public class HelloJob implements Job {

    private static final int[] TIMES = {2,1,4,3,2};
    private static final AtomicInteger COUNT = new AtomicInteger(0);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            log.info("hello count:{},currentTime:{}",COUNT.get(),System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(TIMES[COUNT.getAndIncrement()]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
