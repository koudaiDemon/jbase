package com.cwww.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/2  14:25
 */
//@Component("testEventListener1")
public class TestEventListener1 implements SmartApplicationListener{
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == TestEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return aClass == TestService.class;
    }

    @Override
    @Async
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        try {
            //静静的沉睡3秒钟
            Thread.sleep(3000);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("存入数据库");
    }

    @Override
    public int getOrder() {
        return 0;
    }

//    @Override
//    public void onApplicationEvent(TestEvent testEvent) {
//        try {
//            //静静的沉睡3秒钟
//            Thread.sleep(3000);
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        System.out.println(testEvent.onEvent());
//    }

}
