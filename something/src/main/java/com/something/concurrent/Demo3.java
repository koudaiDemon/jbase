package com.something.concurrent;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/6/8  13:41
 */
public class Demo3 {

    private boolean stopping;

    public void setStopping(boolean stopping) {
        this.stopping = stopping;
    }

    public boolean isStopping() {
        return stopping;
    }


    public static void main(String[] args) {

        for (int i = 0 ; i < 100 ; i++){
            new Demo2.MyThread().start();
        }

    }

}
