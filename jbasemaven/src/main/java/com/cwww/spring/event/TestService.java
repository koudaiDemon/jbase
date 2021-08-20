package com.cwww.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cWww
 * @Title
 * @Description  事件发布者，有事务的操作
 * @date: 2018/7/2  14:28
 */
@Service
@Slf4j
public class TestService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Transactional(rollbackFor = Exception.class)
    public void onEvent() {
        log.info("插入数据库");
        TestEvent event = new TestEvent(this);
        this.applicationContext.publishEvent(event);
    }

}
