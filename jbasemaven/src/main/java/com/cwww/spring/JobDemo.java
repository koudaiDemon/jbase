package com.cwww.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/13  10:24
 */
@Component(value = "job")
public class JobDemo extends AbstractJob {

    @Autowired
    @Qualifier("demo")
    private Demo demo;
//    private String name = "hello";
//    private String age = "18";

    public void showInfo(){
        System.out.println(demo.getSay());
    }


//    @Autowired
//    @Qualifier(value = "demoworld")
//    @Override
//    public void setDemo(Demo demo) {
//        super.setDemo(demo);
//    }
}
