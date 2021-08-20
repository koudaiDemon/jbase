package com.something.qlexpress.impl;

import com.something.qlexpress.Hello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/3/11
 */
@Slf4j
@Service("hello")
public class HelloImpl implements Hello {

    @Override
    public void sayHello() {
        log.info("sayHello");
    }
}
