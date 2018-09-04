package com.something.task.event;

import com.something.task.pojo.Item;

import java.io.Serializable;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/4  11:38
 */
public class Task implements Serializable {

    private int nodeId;
    private String runnerBean;
    private Context<Item> context;
    private boolean runCluster;
    private String groups;

    public Task() {
        this.runCluster = true;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getRunnerBean() {
        return runnerBean;
    }

    public void setRunnerBean(String runnerBean) {
        this.runnerBean = runnerBean;
    }

    public Context<Item> getContext() {
        return context;
    }

    public void setContext(Context<Item> context) {
        this.context = context;
    }

    public boolean isRunCluster() {
        return runCluster;
    }

    public void setRunCluster(boolean runCluster) {
        this.runCluster = runCluster;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}
