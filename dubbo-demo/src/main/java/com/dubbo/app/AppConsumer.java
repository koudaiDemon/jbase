package com.dubbo.app;

import com.dubbo.inter.HelloProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/4/24  14:34
 */
@Slf4j
public class AppConsumer {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dubbo-consumer.xml");
        context.start();
        final HelloProvider bean = context.getBean(HelloProvider.class);
        final String cWww = bean.providerHello("cWww");
        System.out.println(bean);
        System.out.println(cWww);

    }

}
