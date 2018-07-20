package com.cwww.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/2  14:22
 */
public class TestEvent extends ApplicationEvent {

    public TestEvent(Object source) {
        super(source);
    }

    public String onEvent(){
        System.out.println("创建了事件");
//        int i = 1/0;
        return "ON EVENT!!!!!";
    }

}
