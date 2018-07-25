package com.cwww.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author cWww
 * @Title
 * @Description 用于测试Spring 的event listener
 * @date: 2018/7/2  14:35
 */
public class Test {

    public static String test(){
        ApplicationContext ac = new FileSystemXmlApplicationContext("src/main/resources/spring.xml");

        TestService testService = ac.getBean(TestService.class);

        testService.onEvent();


        return "OK";
    }

    public static void main(String[] args) throws Exception {

        System.out.println(Test.test());

    }

}
