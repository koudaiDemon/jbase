package com.something.spring.integration;

import com.something.spring.integration.pojo.User;
import com.something.spring.integration.service.MessageSend;
import com.something.spring.integration.service.TestConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/6  16:53
 */
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-int.xml");
        //Spring官方测试
//        TestConverter simpleGateway = ctx.getBean("simpleGateway", TestConverter.class);
//        System.out.println(simpleGateway.calculateString("abc"));
//        MessageSend messageSendImpl = ctx.getBean("messageSendImpl", MessageSend.class);
//        messageSendImpl.send();

//        MessageChannel inputChannel = ctx.getBean("inputChannel", MessageChannel.class);
//        PollableChannel outputChannel = ctx.getBean("outputChannel", PollableChannel.class);
//        inputChannel.send(MessageBuilder.withPayload(new User("test","123456")).build());
//        LOGGER.info("==> HelloWorldDemo: " + outputChannel.receive().getPayload());
        
    }

}
