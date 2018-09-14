package com.something.task.event;

import com.something.task.cluster.ClusterManager;
import com.something.task.cluster.Node;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/4  17:22
 */
@Component("clusterEventListener")
public class ClusterEventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ClusterEvent) {
            final Node node = ClusterManager.getCurrentNode();
            System.out.println("集群间的信息");
            node.sendMsg(null,event);
        }
    }

}
