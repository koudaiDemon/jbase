package com.something.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/3/2
 */
@Slf4j
public class QLExpressTest {

    private final ExpressRunner runner = new ExpressRunner();

    @Test
    public void demo1() throws Exception{
        final String express = "n=10;sum=0;\n" +
                "for(i=0;i<n;i++){\n" +
                "sum=sum+i;\n" +
                "}\n" +
                "return sum;";

        final DefaultContext<String, Object> context = new DefaultContext<>();

        final Object execute = runner.execute(express, context, null, true, false);
        log.info("execute:[{}]", execute);
    }

    @Test
    public void demo2() throws Exception {
        final String express = "a=1;\n" +
                "b=2;\n" +
                "maxnum = a>b?a:b;";
        final DefaultContext<String, Object> context = new DefaultContext<>();
        final Object execute = runner.execute(express, context, null, true, false);
        log.info("execute:[{}]", execute);
    }

    @Test
    public void demo3() throws Exception {
        //带有int平均分是返回参数
        String express = "int 平均分 = (语文+数学+英语+综合考试.科目2)/4.0;return 平均分";
        String[] names = runner.getOutVarNames(express);
        for(String name : names){
            log.info("name:[{}]", name);
        }
    }

    @Test
    public void demo4() throws Exception {
        runner.loadExpress("Demo");
        final String[] demos = runner.getOutVarNames("Demo");
        for(String name : demos){
            log.info("name:[{}]", name);
        }
    }

    public static void main(String[] args) throws Exception {
        final ExpressRunner runner = new ExpressRunner();
        final DefaultContext<String, Object> context = new DefaultContext<>();
        runner.loadExpress("Demo2");
        final Object demo1 = runner.executeByExpressName("Demo2", context, null, true, true, null);
//        final String[] demos = runner.getOutVarNames("Demo");
//        for(String name : demos){
//            log.info("name:[{}]", name);
//        }
        log.info("demo1:[{}]", demo1);
    }

}
