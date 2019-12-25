package com.something.quartz;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author wei.cai@hand-china.com
 * @Title
 * @Description
 * @date: 2019/9/3  16:29
 */
@Slf4j
public class TimerTaskDemo {

    public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        log.info("start!");
        final Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                log.info("Time's up!");
                timer.cancel();
            }
        }, 3*1000);

    }

}
