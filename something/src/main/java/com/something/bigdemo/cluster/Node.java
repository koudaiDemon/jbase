package com.something.bigdemo.cluster;

import com.something.bigdemo.cache.Manager;
import com.something.bigdemo.db.DBService;
import com.something.bigdemo.db.Item;
import org.jgroups.*;
import org.jgroups.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/29  17:16
 */
public class Node extends ReceiverAdapter {

    private final static Logger LOG = LoggerFactory.getLogger(Node.class);

    /**
     * 集群名称.
     */
    private static final String CLUSTER_NAME = "Persistent";

    /**
     * 节点通道.
     */
    private JChannel channel = null;

    /**
     * 缓存Manager
     */
    private Manager manager;

    /**
     * 以此作为节点间初始化的同步数据.
     */
    private Map<String, String> cacheData = new HashMap<String, String>();

    private ReentrantLock lock = new ReentrantLock();

    public Node(String configXML) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(configXML);
        try {
            DBService.initCache(cacheData);
            manager = new Manager("HelloWorldCache");
            channel = new JChannel(is);
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
    public void sendMsg(Address dest, Item textMsg) {
        Message msg = new Message(dest, textMsg);
        try {
            channel.send(msg);
        } catch (Exception e) {
            LOG.error("消息发送失败!", e);
            // 应自定异常,最好是自定义Exception类型!
            throw new RuntimeException("消息发送失败!", e);
        }
    }

//    @Override
//    public void getState(OutputStream output) throws Exception {
//        //cacheData过大可能会造成节点的状态同步时间过长.
//        lock.lock();
//        try {
//            LOG.info("getState");
//            Util.objectToStream(cacheData, new DataOutputStream(output));
//        }catch(Exception e){
//            throw e;
//        }finally{
//            lock.unlock();
//        }
//    }

    @Override
    public void receive(Message msg) {
        //当前节点不接收自己发送到通道当中的消息.
        if (msg.getSrc().equals(channel.getAddress())) {
            return;
        }
        LOG.info("msg:"+msg.getObject());
        Item item = (Item)msg.getObject();
        switch (item.getPersistentEnum()){
            case SAVE:
                LOG.info("保存对象-缓存"+item);
                manager.setCacheObj(item.getPk(),item.getItem());
                break;
            case DELETE:
                LOG.info("删除对象-缓存"+item);
                manager.removeCacheObj(item.getPk());
                break;
            case UPDATE:
                LOG.info("更新对象-缓存"+item);
                manager.setCacheObj(item.getPk(),item.getItem());
                break;
            default:
                break;
        }
    }

//    @Override
//    public void setState(InputStream input) throws Exception {
//        lock.lock();
//        try {
//            @SuppressWarnings("unchecked")
//            Map<String, String> cacheData = (Map<String, String>) Util.objectFromStream(new DataInputStream(input));
//            LOG.info("setState");
//            this.cacheData.putAll(cacheData);
//        } catch (Exception e) {
//            LOG.error("从主节点同步状态到当前节点发生异常!", e);
//        } finally {
//            lock.unlock();
//        }
//
//    }

//    @Override
//    public void viewAccepted(View view) {
//        LOG.info("当前成员[" + this.channel.getAddressAsString() + "]");
//        LOG.info("getCreator:"+view.getCreator());
//        LOG.info("getMembers:"+view.getMembers());
//        LOG.info("当前节点数据:" + cacheData);
//    }
    /**
     *
     * <pre>
     * 提供一个简单的初始化数据的方法.
     * </pre>
     *
     */
    public void addData(String key,String val){
        if(key!=null&&!key.isEmpty()){
            cacheData.put(key, val);
        }
    }

}
