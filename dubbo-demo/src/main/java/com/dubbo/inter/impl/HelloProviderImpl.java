package com.dubbo.inter.impl;

import com.dubbo.inter.HelloProvider;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/4/24  14:22
 */
@Slf4j
public class HelloProviderImpl implements HelloProvider {

    @Override
    public String providerHello(String name) {
        log.info("HelloProviderImpl,say:{}","hello"+name);
        return "hello"+name;
    }

}
