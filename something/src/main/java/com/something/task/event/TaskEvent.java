package com.something.task.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author cWww
 * @Title
 * @Description Task事件
 * @date: 2018/9/3  17:51
 */
public class TaskEvent extends ApplicationEvent implements ClusterEvent {

    private Integer nodeId;
    private String targetId;

    public TaskEvent(Integer nodeId,String targetId) {
        super(nodeId);
        this.nodeId = nodeId;
        this.targetId = targetId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
