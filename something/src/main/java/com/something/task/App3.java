package com.something.task;


import com.something.task.cluster.ClusterManager;
import com.something.task.cluster.Node;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/4  12:07
 */
public class App3 {

    public static void main(String[] args) {

        System.setProperty("nodeId","2");
        System.setProperty("groups","job");

        final Node node = ClusterManager.getCurrentNode();
    }

}
