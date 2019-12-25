package com.cwww.spring.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/5/8  15:33
 */
public class App {

    public static void main(String[] args) {

        ApplicationContext ac = new FileSystemXmlApplicationContext("jbasemaven/src/main/resources/spring.xml");
        final Demo bean = ac.getBean(Demo.class);
        System.out.println(bean);
    }

}
