package com.something.bigdemo.event;

import com.something.bigdemo.cluster.Node;
import com.something.bigdemo.db.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/3  10:27
 */
public class PersistentEvent extends ApplicationEvent {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistentEvent.class);

    private Item item;
    private Node node;

    public PersistentEvent(Item item){
        super(item);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void onEvent(){
        LOGGER.info("发布事件");
        this.node.sendMsg(null,this.item);
    }

}
