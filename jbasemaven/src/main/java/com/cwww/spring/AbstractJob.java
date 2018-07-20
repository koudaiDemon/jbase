package com.cwww.spring;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/13  10:22
 */
public abstract class AbstractJob {

    protected Demo demo;

    public AbstractJob() {
    }

    public AbstractJob(Demo demo) {
        this.demo = demo;
    }

    public Demo getDemo() {
        return demo;
    }

    @Required
    public void setDemo(Demo demo) {
        this.demo = demo;
    }
}
