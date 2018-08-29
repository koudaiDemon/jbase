package com.cwww.nio;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/23  12:14
 */
public class MyResource implements AutoCloseable {


    @Override
    public void close() throws Exception {

        System.out.println("资源被关闭了");

    }

    public void doSomething(){
        System.out.println("做一些事情");
    }

}
