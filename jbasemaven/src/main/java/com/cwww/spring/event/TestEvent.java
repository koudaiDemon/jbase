package com.cwww.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * @author cWww
 * @Title
 * @Description 消息载体，时间
 * @date: 2018/7/2  14:22
 */
@Slf4j
public class TestEvent extends ApplicationEvent {

    public TestEvent(Object source) {
        super(source);
    }

    public String onEvent(){
        log.info("创建了事件");
//        int i = 1/0;
        return "ON EVENT!!!!!";
    }

}
