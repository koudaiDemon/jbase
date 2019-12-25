package com.cwww.spring.ListDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/5/15  11:08
 */
@Component
public class App {
    @Autowired
    private ActionDemo action1;

    public void hello(){
        action1.run();
    }


}
