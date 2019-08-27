package com.something.task.event.runner;

import com.something.task.event.Context;
import com.something.task.event.Task;
import com.something.task.pojo.Item;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author cWww
 * @Title
 * @Description 线程池修改，数据得上锁
 * @date: 2018/9/4  15:54
 */
public abstract class AbstractTaskRunner<T extends Item> implements TaskRunner<Task>{

    @Override
    public void run(Task task) {
        final Context<T> context = (Context<T>) task.getContext();
        Set<T> data = context.getData();
        Date startDate = context.getStartDate();
        if (null == startDate || (startDate.getTime() - System.currentTimeMillis() < 0)) {
            handleData(data);
        } else {
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.schedule(() -> {
                handleData(data);
            },startDate.getTime()-System.currentTimeMillis(), TimeUnit.MICROSECONDS);
        }
    }

    /**
     * 数据处理
     * @param data
     */
    public abstract void handleData(Set<T> data);

}
