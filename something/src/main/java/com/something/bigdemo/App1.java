package com.something.bigdemo;

import com.something.bigdemo.cluster.Node;
import com.something.bigdemo.cluster.NodeManager;

/**
 * @author cWww
 * @Title
 * @Description 先执行app1，然后执行app
 * @date: 2018/9/3  14:08
 */
public class App1 {

    public static void main(String[] args) {

        final Node node1 = NodeManager.getNode("jgroups-tcp1.xml");

    }

}
