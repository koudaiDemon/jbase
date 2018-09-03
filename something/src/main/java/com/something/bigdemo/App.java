package com.something.bigdemo;

import com.something.bigdemo.cluster.Node;
import com.something.bigdemo.cluster.NodeManager;
import com.something.bigdemo.db.Item;
import com.something.bigdemo.event.PersistentEnum;
import com.something.bigdemo.event.PersistentEvent;
import com.something.bigdemo.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Collections;

/**
 * @author cWww
 * @Title
 * @Description 先执行app1，然后执行app
 * @date: 2018/9/3  10:25
 */
public class App {

    public static void main(String[] args) {

        final Node node = NodeManager.getNode("jgroups-tcp.xml");

        ApplicationContext ac = new FileSystemXmlApplicationContext("something/src/main/resources/spring/spring-base.xml");

        Item<User> item = new Item<>();
        item.setConditions(Collections.emptyList());
        item.setItem(new User("test001","123456","测试"));
        item.setPk(00000001L);
        item.setPersistentEnum(PersistentEnum.SAVE);
        PersistentEvent event = new PersistentEvent(item);
        event.setNode(node);
        ac.publishEvent(event);

        Item<User> item1 = new Item<>();
        item1.setConditions(Collections.emptyList());
        item1.setItem(new User("test001","123456","测试更新"));
        item1.setPk(00000001L);
        item1.setPersistentEnum(PersistentEnum.UPDATE);
        PersistentEvent event1 = new PersistentEvent(item1);
        event1.setNode(node);
        ac.publishEvent(event1);

    }

}
