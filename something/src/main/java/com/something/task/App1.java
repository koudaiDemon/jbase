package com.something.task;

import com.something.task.cluster.ClusterManager;
import com.something.task.cluster.Node;
import com.something.task.event.Context;
import com.something.task.event.Task;
import com.something.task.pojo.Item;
import com.something.task.pojo.User;
import com.something.task.service.TaskService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Collections;

/**
 * @author cWww
 * @Title
 * 测试task任务集群间调度,通过将需要处理的数据以JSON存入redis(方便)
 * 然后集群通过获取到信息以后,获取需要处理的数据(这里需要对数据进行上锁操作)
 * 本例子只是一个demo,后续需要大量的修改
 * 目的是为了达到task任务可以在指定的集群组运行
 * 比如:现在有5个用户注册的操作在本机上，然后通过本机直接将数据给到task服务器
 * 然后通过task服务器上指定的方式进行任务解决。
 * 涉及技术，JGROUPS,Jedis,Spring-Event
 * @date: 2018/9/3  17:26
 */
public class App1 {

    public static void main(String[] args) throws Exception {

        System.setProperty("nodeId","0");
        System.setProperty("groups","task");
        final Node node = ClusterManager.getCurrentNode();

        ApplicationContext ac = new FileSystemXmlApplicationContext("something/src/main/resources/spring/spring-base.xml");
        final TaskService bean = ac.getBean(TaskService.class);
        Task task = new Task();
        task.setRunnerBean("userTaskRunner");
        task.setNodeId(2);
        task.setGroups("job");
        Context<Item> userContext = new Context<>();
        User user = new User("test","test001","testAddr");
        user.setPk("00000001");
        userContext.setData(Collections.singleton(user));
        task.setContext(userContext);
        bean.scheduleTask(task);

    }

}
