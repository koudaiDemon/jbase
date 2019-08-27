package com.something.task.service;

import com.something.task.event.Task;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/3  17:48
 */
public interface TaskService {

    /**
     * 执行Task
     * @param task
     */
    void scheduleTask(Task task);

}
