package com.cwww.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author cWww
 * @Title App
 * @Description 应用
 * @date: 2019/4/12  8:55
 */
public class App {

    public static void main(String[] args) {
        final ApplicationContext ac = new FileSystemXmlApplicationContext("jbasemaven/src/main/resources/spring.xml");
        final HelloInterface helloInterface1 = ac.getBean("helloInterface1", HelloInterface.class);
//        final HelloInterface helloInterface2 = ac.getBean("helloInterface2", HelloInterface.class);

        helloInterface1.hello("aaaa");
//        helloInterface2.hello();


        ((FileSystemXmlApplicationContext) ac).close();

    }

}
