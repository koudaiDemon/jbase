package com.dubbo.inter;

/**
 * @author cWww
 * @Title HelloProvider
 * @Description provider
 * @date: 2019/4/24  14:20
 */
public interface HelloProvider {

    /**
     * 提供Hello
     * @return hello
     */
    String providerHello(String name);

}
