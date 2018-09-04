package com.something.task.service.impl;

import com.something.task.event.Task;
import com.something.task.event.runner.TaskRunner;
import com.something.task.pojo.Item;
import com.something.task.service.TaskStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/4  15:07
 */
@Component("taskStrategy")
public class TaskStrategyImpl implements TaskStrategy,ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void run(Task task) {
        final String runnerBean = task.getRunnerBean();
        TaskRunner<Task> taskRunner = (TaskRunner<Task>)applicationContext.getBean(runnerBean);
        taskRunner.run(task);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
