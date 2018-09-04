package com.something.task.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author cWww
 * @Title
 * @Description 后面换成接口
 * @date: 2018/9/4  15:31
 */
@Component("eventService")
public class EventService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void publish(ApplicationEvent event){
        applicationContext.publishEvent(event);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
