package com.something.task.cluster;

import java.io.Serializable;

/**
 * @author cWww
 * @Title 节点信息
 * @Description
 * @date: 2018/9/4  12:31
 */
public class NodeMessage implements Serializable {

    private String nodeId;
    private String ipAddress;
    private String groups;
    private boolean isAlive;

    public NodeMessage(){
        this.isAlive = true;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}
