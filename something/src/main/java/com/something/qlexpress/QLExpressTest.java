package com.something.qlexpress;

import com.ql.util.express.*;
import com.ql.util.express.instruction.op.OperatorBase;
import com.ql.util.express.instruction.op.OperatorEqualsLessMore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/3/2
 */
@Slf4j
public class QLExpressTest {

    private final ExpressRunner runner = new ExpressRunner();

    private static final Integer COUNT = 1000000;

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
    public void demoThread() throws Exception {
        final ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (int i = 0 ; i < 100 ; i++) {
            executorService.submit(() -> {
                for (int j = 0 ; j < 100 ; j++) {
                    final String express = "return a";
                    final String a = Thread.currentThread().getName();
                    final DefaultContext<String, Object> context = new DefaultContext<>();
                    context.put("a", a);
                    final Object execute;
                    try {
                        execute = runner.execute(express, context, null, true, false);
                        if (!execute.equals(a)) {
                            log.error("error==============================================");
                        }
                        log.info("execute:[{}]", execute);
                    } catch (Exception e) {
                        log.error("error", e);
                    }
                }
            });
        }

        Thread.sleep(6000);

    }

    @Test
    public void demo2() throws Exception {
        final String express = "return NewList('aaaa','bbbb','ccc')";
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
    public void call() throws Exception {
        final Order orderObj = null;
//                Order.builder().code("code").amount(BigDecimal.TEN).build();
        final Map<String, Object> orderMap = new HashMap<>(10);
        orderMap.put("code", "1111");
        orderMap.put("amount", 110.0);

        promotion(orderMap);

        promotion(orderObj);
    }

    public void promotion(final Order order) throws Exception {
        final String exp = "return param.order.amount > 100;";
        final List<String> params = null;
        final Map<String, Object> param = new HashMap<>(3);
        param.put("order", order);
        if (runRule(exp, param)) {
            log.info("do Action");
//            order.setAmount(order.getAmount().subtract(new BigDecimal(10)));
        }
        log.info("after rule,order:[{}]", order);
    }

    public void promotion(final Map<String, Object> order) throws Exception {
        final String exp = "return param.order.amount > 100;";
        final List<String> params = null;
        final Map<String, Object> param = new HashMap<>(3);
        param.put("order", order);
        if (runRule(exp, param)) {
            log.info("do Action");
            order.put("amount", (Double)order.getOrDefault("amount", 0.0) - 10);
        }

        log.info("after rule,order:[{}]", order);
    }

    public boolean runRule(String exp, Map<String, Object> param) throws Exception{
        final DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.putAll(param);
        final Object execute = runner.execute(exp, context, null, false, false, null);
        log.info("execute:[{}]", execute);
        return (Boolean) execute;
    }

    @Test
    public void demo7() throws Exception  {
//        "return (order.amount == 1000.0 && (order.type == SALES || order.type == REFUND || (user.sex == MAIL || user.age == 18)));"
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0 ; i < COUNT ; i++) {
            final String exp = "return (order.amount == 1000.0 && (order.type == 'SALES' || order.type == 'REFUND' || (user.sex == 'MAIL' || user.age == 18)));";
            final DefaultContext<String, Object> context = new DefaultContext<String, Object>();
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final Date parse = simpleDateFormat.parse("2019-11-11 11:11:11");
            final Order order = Order.builder().amount((double) i).creationTime(parse).type("SALES").build();
            final User user = User.builder().age(17).sex("女").build();
            context.put("order", order);
            context.put("user", user);
            final Object execute = runner.execute(exp, context, null, true, false, null);
        }
        stopWatch.stop();
        log.info("spend:[{}]", stopWatch.getLastTaskTimeMillis());
//        log.info("execute:[{}]", execute);
    }

    @Test
    public void test8() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0 ; i < COUNT ; i++) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final Date parse = simpleDateFormat.parse("2019-11-11 11:11:11");
            final Order order = Order.builder().amount((double) i).creationTime(parse).type("SALES").build();
            final User user = User.builder().age(17).sex("女").build();
            Boolean b = order.getAmount() == 1000.0 && (order.getType().equals("SALES") || order.getType().equals("REFUND") || (user.getSex().equals("MAIL") || user.getAge() == 18));
        }
        stopWatch.stop();
        log.info("spend:[{}]", stopWatch.getLastTaskTimeMillis());
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

//        final ExpressRunner runner = new ExpressRunner();
//        final String exp = "hello.sayHello()";
//        final ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-qlexpress.xml");
//        final IExpressContext<String, Object> context = new QLExpressContext(new HashMap<String, Object>(10), ctx);
//        runner.execute(exp, context,null,false,true);

        String[] strings = {"yyyy-MM-dd HH:mm:ss"};




        log.info("Date:[{}]", DateUtils.parseDate("2019-11-11 11:11:11", strings));

    }

}
