package com.cwww.spring.aop;

import org.springframework.stereotype.Component;

/**
 * @author cWww
 * @Title HelloInterfaceImpl1
 * @Description HelloInterfaceImpl1
 * @date: 2019/4/12  8:53
 */
@Component(value = "helloInterface2")
public class HelloInterfaceImpl2 implements HelloInterface {
    @Override
    public void hello() {
        System.out.println("HelloInterfaceImpl2 hello()");
    }

    @Override
    public void doPrint() {
        System.out.println("HelloInterfaceImpl2 doPrint()");
    }

    @Override
    public String hello(String msg) {
        System.out.println("HelloInterfaceImpl2 hello()"+msg);
        return "HelloInterfaceImpl2"+msg;
    }

    @Override
    public String request(String request) {
        //dosometing()
        System.out.println("handler,request");
        return request;
    }
}
