package com.cwww.spring.ListDemo;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/4/11  20:17
 */
public class ActionDemo2 implements ActionDemo {
    @Override
    public String getString() {
        return "action2";
    }

    @Override
    public void run() {
        System.out.println("run action2()");
    }
}
