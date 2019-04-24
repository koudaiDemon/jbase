package com.cwww.spring.ListDemo;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/4/11  20:17
 */
public class ActionDemo1 implements ActionDemo {
    @Override
    public String getString() {
        return "action1";
    }

    @Override
    public void run() {
        System.out.println("run action1()");
    }
}
