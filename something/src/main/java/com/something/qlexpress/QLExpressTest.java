package com.something.qlexpress;

import com.ql.util.express.*;
import com.ql.util.express.exception.QLBizException;
import com.ql.util.express.instruction.op.OperatorBase;
import com.ql.util.express.instruction.op.OperatorEqualsLessMore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;
import org.redisson.client.RedisException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StopWatch;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/3/2
 */
@Slf4j
@SuppressWarnings("all")
public class QLExpressTest {

    private final ExpressRunner runner = new ExpressRunner();
    private final ExpressRunner runner2 = new ExpressRunner();

    private static final Integer COUNT = 100000;

    @Test
    public void demo1() throws Exception {
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

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
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
        final String express = "return user.username";
        final DefaultContext<String, Object> context = new DefaultContext<>();
        final User user = User.builder().username("aaaaaa").build();
        context.put("user", user);
        try {
            final Object execute = runner.execute(express, context, null, true, false);
            log.info("execute:[{}]", execute);
        } catch (RuntimeException e) {
            log.info("asdasdasdadadadasdsa");
        }

    }

    @Test
    public void demo21() throws Exception {
        final String express = "return order.firstOrderFlag == 0";
        final DefaultContext<String, Object> context = new DefaultContext<>();


        final HashMap<String, Object> order = new HashMap<>(3);
        order.put("customerAccount", "A");
        order.put("firstOrderFlag", 0);

        context.put("order", order);


        final Object execute = runner.execute(express, context, null, true, false);
        log.info("execute:[{}]", execute);
    }

    @Test
    public void demo22() throws Exception {
        final String express = "return users.username";
        final QLContext context = new QLContext();
        try {
            final Object execute = runner.execute(express, context, null, true, false);
            log.info("execute:[{}]", execute);
        } catch (QLBizException e) {
            log.info("adasdsadasdasdasdadadadasdasd");
        }
    }

    @Test
    public void demo3() throws Exception {
        //带有int平均分是返回参数
        String express = "int 平均分 = (语文+数学+英语+综合考试.科目2)/4.0;return 平均分";
        String[] names = runner.getOutVarNames(express);
        for (String name : names) {
            log.info("name:[{}]", name);
        }
    }

    @Test
    public void demo4() throws Exception {
        runner.loadExpress("Demo");
        final String[] demos = runner.getOutVarNames("Demo");
        for (String name : demos) {
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
            parent.put(key, value);
            return null;
        }
    }

    class OperatorExists extends Operator {

        private final ExpressRunner runner;

        public OperatorExists(final ExpressRunner runner, final String aName) {
            this.runner = runner;
            this.name = aName;
        }

        @Override
        public Object executeInner(Object[] list) throws Exception {
            final Collection<?> first = (Collection<?>) list[0];
            final String second = (String) list[1];
            for (Object object : first) {
                final DefaultContext<String, Object> context = new DefaultContext<>();
                context.put(object.getClass().getSimpleName().toLowerCase(), object);
                final Boolean execute = (Boolean) runner.execute(second, context, null, true, false, null);
                if (BooleanUtils.isTrue(execute)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Test
    public void demo66() throws Exception {
        final String express = "return exists(users, express)";
        final DefaultContext<String, Object> context = new DefaultContext<>();
        final OperatorExists exists = new OperatorExists(runner2, "exists");
        runner.addFunction("exists", exists);

        final User user1 = User.builder().username("aaaa").age(18).build();
        final User user2 = User.builder().username("bbbb").age(30).build();
        final User user3 = User.builder().username("cccc").age(50).build();

        Arrays.asList(user1, user2, user3).stream().anyMatch(e -> e.getAge() > 40);

        context.put("users", Arrays.asList(user1, user2, user3));
        context.put("express", "user.age > 40 && user.username == 'cccc'");

        final Object demo1 = runner.execute(express, context, null, true, false, null);
        log.info("demo1:[{}]", demo1);
    }

    @Test
    public void demo666() throws Exception {
        final String express = "return users.stream().anyMatch(e -> e.getAge() > 40)";
        final DefaultContext<String, Object> context = new DefaultContext<>();
//        final OperatorExists exists = new OperatorExists(runner2, "exists");
//        runner.addFunction("exists", exists);

        final User user1 = User.builder().username("aaaa").age(18).build();
        final User user2 = User.builder().username("bbbb").age(30).build();
        final User user3 = User.builder().username("cccc").age(50).build();

//        Arrays.asList(user1, user2, user3);

        context.put("users", Arrays.asList(user1, user2, user3));
//        context.put("express", "user.age > 40 && user.username == 'cccc'");

        final Object demo1 = runner.execute(express, context, null, true, false, null);
        log.info("demo1:[{}]", demo1);
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
        runner.addOperatorWithAlias("如果", "if", null);
        runner.addOperatorWithAlias("则", "then", null);
        runner.addOperatorWithAlias("否则", "else", null);

        final String exp = "如果  (语文+数学+英语>270) 则 {return 1;} 否则 {return 0;}";
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("语文", "90");
        context.put("数学", "100");
        context.put("英语", "90");
        final Object execute = runner.execute(exp, context, null, false, false, null);
        log.info("execute:[{}]", execute);
    }


    @Test
    public void demo8() throws Exception {

        final List<Order.OrderEntry> entries = new ArrayList<>();
        entries.add(Order.OrderEntry.builder().sku("sku1").spu("spu1").qty(10).price(new BigDecimal(100)).build());
        entries.add(Order.OrderEntry.builder().sku("sku2").spu("spu2").qty(5).price(new BigDecimal(40)).build());

        final Order order = Order.builder().creationTime(new Date()).code("aaaaaa").entries(entries).type("SALE").build();
        final String express = "return (spuCondition(order, \"anyIn\", \"spu2\").compareTo(new java.math.BigDecimal(\"3\")) > 0)";
        final DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("order", order);
        runner.addFunctionOfClassMethod("spuCondition", QLExpressTest.class.getName(), "spuCondition", new Class[]{Order.class, String.class, String.class}, null);

        final Object demo1 = runner.execute(express, context, null, true, false, null);
        log.info("demo1:[{}]", demo1);
    }

//    class OperatorExists extends Operator {
//
//        private final ExpressRunner runner;
//
//        public OperatorExists(final ExpressRunner runner, final String aName) {
//            this.runner = runner;
//            this.name = aName;
//        }
//
//        @Override
//        public Object executeInner(Object[] list) throws Exception {
//            final Collection<?> first = (Collection<?>)list[0];
//            final String second = (String)list[1];
//            for (Object object : first) {
//                final DefaultContext<String, Object> context = new DefaultContext<>();
//                context.put(object.getClass().getSimpleName().toLowerCase(), object);
//                final Boolean execute = (Boolean)runner.execute(second, context, null, true, false, null);
//                if (BooleanUtils.isTrue(execute)) {
//                    return true;
//                }
//            }
//            return false;
//        }
//    }


    public static BigDecimal spuCondition(final Order order,
                                          final String productCondition,
                                          final String productValue) {
        final List<Order.OrderEntry> entries = order.getEntries();

        if ("anyIn".equalsIgnoreCase(productCondition)) {

            final String[] split = productValue.split(",");

            final List<String> strings = Arrays.asList(split);

            return new BigDecimal(entries.stream().filter(e -> strings.contains(e.getSpu())).mapToInt(Order.OrderEntry::getQty).sum());
        }

        return BigDecimal.ZERO;
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
            order.put("amount", (Double) order.getOrDefault("amount", 0.0) - 10);
        }

        log.info("after rule,order:[{}]", order);
    }

    public boolean runRule(String exp, Map<String, Object> param) throws Exception {
        final DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.putAll(param);
        final Object execute = runner.execute(exp, context, null, false, false, null);
        log.info("execute:[{}]", execute);
        return (Boolean) execute;
    }

    @Test
    public void demo7() throws Exception {
//        "return (order.amount == 1000.0 && (order.type == SALES || order.type == REFUND || (user.sex == MAIL || user.age == 18)));"
        final String exp = "return (order.amount == 1000.0 && (order.type == 'SALES' || order.type == 'REFUND' || (user.sex == 'MAIL' || user.age == 18)));";
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < COUNT; i++) {
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
    public void test71() throws Exception {
        final String exp = "return (order.entries listGet 'price') anyGreater new java.math.BigDecimal(\"139\");";

        final QLContext context = new QLContext();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date parse = simpleDateFormat.parse("2019-11-11 11:11:11");
        final Order order = Order.builder().amount(100.0).creationTime(parse).type("SALES").build();
        final List<Order.OrderEntry> entries = new ArrayList<>();
        entries.add(new Order.OrderEntry.OrderEntryBuilder().sku("TEST001").price(BigDecimal.valueOf(100)).build());
        entries.add(new Order.OrderEntry.OrderEntryBuilder().sku("TEST002").price(BigDecimal.valueOf(120)).build());
        entries.add(new Order.OrderEntry.OrderEntryBuilder().sku("TEST003").price(BigDecimal.valueOf(130)).build());
        order.setEntries(entries);

//        runner.addOperator("anyIn", new OperatorAnyIn("anyIn"));

        runner.addOperator("anyGreater", new Operator() {
            @Override
            public Object executeInner(Object[] list) throws Exception {
                List<Object> source = (List<Object>) list[0];

                if (CollectionUtils.isEmpty(source)) {
                    return false;
                }

                Object compare = list[1];

                boolean result = false;




                for (Object obj : source) {

                    if (result) {
                        return result;
                    }

                    if (obj.getClass().equals(compare.getClass()) && obj instanceof Comparable) {
                        final Comparable comparableA = (Comparable) obj;
                        final Comparable comparableB = (Comparable) compare;

                        result = comparableA.compareTo(comparableB) > 0;
                    }
                }

                return result;
            }
        });

        runner.addOperator("listGet", new Operator() {
            @Override
            public Object executeInner(Object[] list) throws Exception {
                List<Object> source = (List<Object>) list[0];

                if (CollectionUtils.isEmpty(source)) {
                    return Collections.emptyList();
                }

                String propety = (String) list[1];

                final Class<?> aClass = source.get(0).getClass();
                final Field field = ReflectionUtils.findField(aClass, propety);
                ReflectionUtils.makeAccessible(field);

                final List<Object> result = new ArrayList<>(source.size());

                for (Object obj : source) {
                    result.add(ReflectionUtils.getField(field, obj));
                }

                return result;
            }
        });

        context.put("order", order);
        final Object execute = runner.execute(exp, context, null, true, false, null);

        log.info("result:[{}]", execute);
    }

    @Test
    public void test8() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < COUNT; i++) {
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
    public void test() throws Exception {
        OperatorBase op = new OperatorContextPut("contextPut");
        runner.addFunction("contextPut", op);
        String exp = "contextPut('success','false');contextPut('error','错误信息');contextPut('warning','提醒信息')";
        IExpressContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("success", "true");
        Object result = runner.execute(exp, context, null, false, true);
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
