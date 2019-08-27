package com.something.bigdemo.cluster;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author cWww
 * @Title
 * @Description 节点的管理,利用Map缓存
 * @date: 2018/9/3  12:31
 */
public class NodeManager {

    private static Map<String,Node> nodeMap = new HashMap<>();

    private NodeManager(){
    }

    public static Node getNode(String config){
        Objects.requireNonNull(config);
        if (nodeMap.containsKey(config)) {
            return nodeMap.get(config);
        } else {
            Node node = new Node(config);
            nodeMap.put(config,node);
            return node;
        }
    }

}
