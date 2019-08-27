package com.cwww.nio;

/**
 * @author cWww
 * @Title
 * @Description 测试AutoClose
 * @date: 2018/8/23  12:13
 */
public class Demo8 {

    public static void main(String[] args) {



        try
            (
                MyResource myResource = null
            )
        {
            myResource.doSomething();

        } catch (Exception e){

        }

    }

}
