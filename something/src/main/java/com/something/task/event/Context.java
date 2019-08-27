package com.something.task.event;

import com.something.task.pojo.Item;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author cWww
 * @Title
 * @Description 处理逻辑的上下文
 * @date: 2018/9/3  17:26
 */
public class Context<T extends Item> implements Serializable {
    private String title;
    private String nodeId;
    private String runnerBean;
    private int retry;
    private Set<T> data;
    private Date startDate;

    public Context(){
        this.retry = 0;
        this.startDate = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getRunnerBean() {
        return runnerBean;
    }

    public void setRunnerBean(String runnerBean) {
        this.runnerBean = runnerBean;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public Set<T> getData() {
        return data;
    }

    public void setData(Set<T> data) {
        this.data = data;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
