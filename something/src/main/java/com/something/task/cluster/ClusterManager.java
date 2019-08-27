package com.something.task.cluster;

import com.alibaba.fastjson.JSON;
import com.something.redis.RedisUtil;
import com.something.task.pojo.Constant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cWww
 * @Title 集群控制器
 * @Description
 * @date: 2018/9/4  12:13
 */
public class ClusterManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterManager.class);
    private static Map<Integer,Node> cache = new HashMap<>();

    private static Jedis jedis = RedisUtil.getInstance().getJedis();

    /**
     * 获取所有的节点信息
     * @return
     */
    public static List<NodeMessage> getAllNodes(){
        try {
            return jedis.hgetAll(Constant.REDIS_KYE).values().stream().map(e -> JSON.parseObject(e, NodeMessage.class)).collect(Collectors.toList());
        } catch (Exception e){
            LOGGER.error("Redis出错",e);
        } finally {
            RedisUtil.returnResource(jedis);
        }
        return Collections.emptyList();
    }

    /**
     * 获取当前节点
     * @return
     */
    public static Node getCurrentNode(){
        String nodeId = System.getProperty(Constant.CLUSTER_KEY);
        if (StringUtils.isNotEmpty(nodeId)) {
            if (cache.containsKey(Integer.valueOf(nodeId))) {
                return cache.get(Integer.valueOf(nodeId));
            }
            String config = Constant.JGROUPS_XML+(Integer.valueOf(nodeId) == 0 ? "":nodeId)+Constant.XML;
            Node node = new Node(config);
            cache.put(Integer.valueOf(nodeId),node);
            return node;
        }
        return null;
    }

}
