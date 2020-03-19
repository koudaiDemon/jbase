package com.something.qlexpress;

import com.ql.util.express.*;
import com.ql.util.express.instruction.op.OperatorBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.*;

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

        new Date();
        new SimpleDateFormat();
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

    class OperatorContextPut extends OperatorBase {

        public OperatorContextPut(String aName) {
            this.name = aName;
        }

        @Override
        public OperateData executeInner(InstructionSetContext parent, ArraySwap list) throws Exception {
            String key = list.get(0).toString();
            Object value = list.get(1);
            parent.put(key,value);
            return null;
        }
    }

    @Test
    public void demo5() throws Exception {
        final DefaultContext<String, Object> context = new DefaultContext<>();
        runner.loadExpress("Demo");
        final Object demo1 = runner.executeByExpressName("Demo", context, null, true, true, null);
        log.info("demo1:[{}]", demo1);
    }

    @Test
    public void demo6() throws Exception {
        runner.addOperatorWithAlias("如果", "if",null);
        runner.addOperatorWithAlias("则", "then",null);
        runner.addOperatorWithAlias("否则", "else",null);

        final String exp = "如果  (语文+数学+英语>270) 则 {return 1;} 否则 {return 0;}";
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("语文","90");
        context.put("数学","100");
        context.put("英语","90");
        final Object execute = runner.execute(exp, context, null, false, false, null);
        log.info("execute:[{}]", execute);
    }

    @Test
    public void test() throws Exception{
        OperatorBase op = new OperatorContextPut("contextPut");
        runner.addFunction("contextPut",op);
        String exp = "contextPut('success','false');contextPut('error','错误信息');contextPut('warning','提醒信息')";
        IExpressContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("success","true");
        Object result = runner.execute(exp,context,null,false,true);
        log.info("{}", result);
        log.info("{}", context);
    }

    public static void main(String[] args) throws Exception {

        final ExpressRunner runner = new ExpressRunner();
        final String exp = "hello.sayHello()";
        final ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-qlexpress.xml");
        final IExpressContext<String, Object> context = new QLExpressContext(new HashMap<String, Object>(10), ctx);
        runner.execute(exp, context,null,false,true);

    }

}
