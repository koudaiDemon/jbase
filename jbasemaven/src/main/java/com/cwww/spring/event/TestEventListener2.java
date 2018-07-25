package com.cwww.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author cWww
 * @Title
 * @Description 可以选择执行顺序的事件监听
 * @date: 2018/7/2  14:25
 */
//@Component("testEventListener2")
public class TestEventListener2 implements SmartApplicationListener{
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == TestEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return aClass == TestService.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        System.out.println("发送邮件");
    }

    @Override
    public int getOrder() {
        return 1;
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
