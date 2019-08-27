package com.something.task.event.runner;

import com.something.task.event.Task;

/**
 * @author cWww
 * @Title
 * @Description 执行接口
 * @date: 2018/9/4  11:36
 */
public interface TaskRunner<T extends Task> {

    /**
     * 用于执行的真实执行体
     * @param task
     */
    void run(T task);

}
