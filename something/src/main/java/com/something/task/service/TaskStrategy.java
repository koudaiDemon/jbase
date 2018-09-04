package com.something.task.service;

import com.something.task.event.Task;

/**
 * @author cWww
 * @Title
 * @Description 被service调用
 * @date: 2018/9/4  15:05
 */
public interface TaskStrategy {

    /**
     * 执行task命令的实体
     * @param task
     */
    void run(Task task);

}
