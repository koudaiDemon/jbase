package com.something.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.TimerTask;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author wei.cai@hand-china.com
 * @Title DataSourceQuartzDemo
 * @Description 任务调度数据库方式
 * @date: 2019/9/2  11:10
 */
@Slf4j
public class DataSourceQuartzDemo {

    public static void main(String[] args) {

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//            TimerTask

//            scheduler.getJobDetail(JobKey.jobKey("job2","job2"));

//            // define the job and tie it to our HelloJob class
//            JobDetail job = newJob(HelloJob.class)
//                    .withIdentity("job2", "group2")
//                    .build();
//
//            // Trigger the job to run now, and then repeat every 40 seconds
//            Trigger trigger = newTrigger()
//                    .withIdentity("trigger2", "group2")
//                    .startNow()
//                    .withSchedule(simpleSchedule()
//                            .withIntervalInSeconds(3)
//                            .withRepeatCount(50))
//                    .build();
//
//            // Tell quartz to schedule the job using our trigger
//            scheduler.scheduleJob(job, trigger);
//
//            scheduler.start();

            scheduler.start();

//            scheduler.shutdown();

        } catch (SchedulerException e) {
            log.error("QuartzDemo",e);
        }

    }

}
