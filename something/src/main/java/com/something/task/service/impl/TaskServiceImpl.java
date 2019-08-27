package com.something.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.something.redis.RedisUtil;
import com.something.task.cluster.ClusterManager;
import com.something.task.cluster.Node;
import com.something.task.event.Context;
import com.something.task.event.Task;
import com.something.task.event.TaskEvent;
import com.something.task.pojo.Constant;
import com.something.task.service.TaskService;
import com.something.task.service.TaskStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/4  11:47
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService,ApplicationContextAware{

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskStrategy taskStrategy;

    private ApplicationContext applicationContext;

    @Override
    public void scheduleTask(Task task) {
        if (task.isRunCluster()) {
            Jedis jedis = RedisUtil.getInstance().getJedis();
            LOGGER.info("存入数据库:{}",JSON.toJSONString(task));
            jedis.set("node-"+task.getNodeId(), JSON.toJSONString(task));
            TaskEvent taskEvent = new TaskEvent(Integer.valueOf(System.getProperty(Constant.CLUSTER_KEY)),task.getGroups());
            applicationContext.publishEvent(taskEvent);
        } else {
            taskStrategy.run(task);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
