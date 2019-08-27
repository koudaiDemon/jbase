package com.dubbo.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cWww
 * @Title APP
 * @Description AppProvider
 * @date: 2019/4/24  14:04
 */
@Slf4j
public class AppProvider {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dubbo.xml");
        context.start();
        System.in.read(); // 按任意键退出

    }

}
