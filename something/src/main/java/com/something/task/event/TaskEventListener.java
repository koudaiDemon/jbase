package com.something.task.event;

import com.alibaba.fastjson.JSON;
import com.something.redis.RedisUtil;
import com.something.task.cluster.ClusterManager;
import com.something.task.cluster.Node;
import com.something.task.pojo.Constant;
import com.something.task.service.TaskStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/4  11:24
 */
@Component("taskEventListener")
public class TaskEventListener implements ApplicationListener<TaskEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskEventListener.class);

    @Autowired
    private TaskStrategy taskStrategy;

    @Override
    public void onApplicationEvent(TaskEvent taskEvent) {
        final String groups = System.getProperty(Constant.GROUPS_KEY);
        if (groups.contains(taskEvent.getTargetId())) {
            final Node node = ClusterManager.getCurrentNode();
            Jedis jedis = RedisUtil.getInstance().getJedis();
            String task = jedis.get(node.getCurrentNode());
            LOGGER.info("task:{}",task);
            taskStrategy.run(JSON.parseObject(task,Task.class));
        }
    }

}
