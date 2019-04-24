package com.something.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cWww
 * @Title QuartzDemo
 * @Description Quartz任务调度Demo
 * @date: 2019/2/22  14:23
 */
public class QuartzDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzDemo.class);

    public static void main(String[] args) {

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.start();

            scheduler.shutdown();

        } catch (SchedulerException e) {
            LOGGER.error("QuartzDemo",e);
        }

    }

}
