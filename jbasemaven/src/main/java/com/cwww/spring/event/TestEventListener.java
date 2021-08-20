package com.cwww.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author cWww
 * @Title
 * @Description 消息监听者，异步的处理消息，可参考async包
 * @date: 2018/7/2  14:25
 */
@Component("testEventListener")
@Slf4j
public class TestEventListener implements ApplicationListener<TestEvent> {

    @Override
//    @Async
    public void onApplicationEvent(TestEvent testEvent) {
        try {
            //静静的沉睡3秒钟
            Thread.sleep(3000);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        log.info(testEvent.onEvent());
    }

}
