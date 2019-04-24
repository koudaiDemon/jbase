package com.cwww.spring.aop;

/**
 * @author cWww
 * @Title HelloInterface
 * @Description HelloInterface
 * @date: 2019/4/12  8:52
 */
public interface HelloInterface {

    /**
     * hello
     */
    void hello();

    /**
     * doPrint
     */
    void doPrint();

    /**
     * hello
     * @param msg
     * @return
     */
    String hello(String msg);


    /**
     * http Request请求
     * @param request
     * @return
     */
    String request(String request);

}
