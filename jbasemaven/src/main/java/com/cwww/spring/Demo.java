package com.cwww.spring;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/13  15:43
 */
public class Demo {

    private String say;

    public Demo() {
    }

    public void init(){
        System.out.println("=======================");
    }

    public Demo(String say) {
        this.say = say;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }
}
