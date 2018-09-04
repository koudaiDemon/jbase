package com.something.task.cluster;

import com.alibaba.fastjson.JSON;
import com.something.redis.RedisUtil;
import com.something.task.event.EventService;
import com.something.task.event.TaskEvent;
import com.something.task.pojo.Constant;
import com.something.task.service.TaskService;
import org.jgroups.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.InputStream;

/**
 * @author cWww
 * @Title
 * @Description 集群中的节点
 * @date: 2018/8/29  17:16
 */
public class Node extends ReceiverAdapter {

    private final static Logger LOG = LoggerFactory.getLogger(Node.class);

    /**
     * 集群名称.
     */
    private static final String CLUSTER_NAME = "Task";

    /**
     * Redis缓存
     */
    private Jedis jedis = RedisUtil.getInstance().getJedis();

    /**
     * 节点通道.
     */
    private JChannel channel = null;

    public Node(String configXML) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(configXML);
        try {
            channel = new JChannel(is);
            channel.setName("node-"+System.getProperty(Constant.CLUSTER_KEY));
            channel.setReceiver(this);
            channel.connect(CLUSTER_NAME);
            channel.getState(null,50000);
        } catch (Exception e) {
            LOG.error("启动节点异常!", e);
            throw new RuntimeException("启动节点异常!", e);
        }
    }

    /**
     *
     * <pre>
     * 发送消息给目标地址.
     * </pre>
     *
     * @param dest
     *            为空表示发给所有节点.
     * @param textMsg
     *            消息.
     */
    public void sendMsg(Address dest, Object textMsg) {
        Message msg = new Message(dest, textMsg);
        try {
            channel.send(msg);
        } catch (Exception e) {
            LOG.error("消息发送失败!", e);
            // 应自定异常,最好是自定义Exception类型!
            throw new RuntimeException("消息发送失败!", e);
        }
    }

    @Override
    public void receive(Message msg) {
        //当前节点不接收自己发送到通道当中的消息.
        if (msg.getSrc().equals(channel.getAddress())) {
            return;
        }
        LOG.info("msg:"+msg.getObject());
        ApplicationEvent taskEvent = (ApplicationEvent)msg.getObject();
        if (taskEvent instanceof TaskEvent) {
            //偷懒直接使用application创建，后面需要修改
            ApplicationContext ac = new FileSystemXmlApplicationContext("something/src/main/resources/spring/spring-base.xml");
            ac.publishEvent(taskEvent);
        }

    }

    @Override
    public void viewAccepted(View view) {
        view.getMembers().forEach(e->{
            final NodeMessage nodeMessage = new NodeMessage();
            nodeMessage.setNodeId(System.getProperty(Constant.CLUSTER_KEY));
            nodeMessage.setIpAddress(e.toString());
            nodeMessage.setGroups(System.getProperty(Constant.GROUPS_KEY));
            if (!jedis.hexists(Constant.REDIS_KYE, nodeMessage.getNodeId())) {
                LOG.info("redis add node,{}",JSON.toJSONString(nodeMessage));
                jedis.hset(Constant.REDIS_KYE,nodeMessage.getNodeId(),JSON.toJSONString(nodeMessage));
            }
        });
    }

    public String getCurrentNode(){
        return channel.getAddress().toString();
    }

}
