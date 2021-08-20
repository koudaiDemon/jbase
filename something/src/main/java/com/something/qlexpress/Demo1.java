package com.something.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/3/2
 */
@Slf4j
public class Demo1 {

    public static void main(String[] args) throws Exception {
        final ExpressRunner runner = new ExpressRunner();
        final DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a",1);
        context.put("b",2);
        context.put("c",3);
        final String express = "a+b*c";
        final Object r = runner.execute(express, context, null, true, false);
        log.info("r:[{}]", r);
    }

}
