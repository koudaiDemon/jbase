package com.cwww.rule;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/3/5
 */
@Slf4j
public class Demo {

    public static void main(String[] args) {


    }

    @Test
    public void test() {
        final Rule rule = new Rule();
        rule.setCode("test");
        rule.setAction("action");
        final Condition condition = Condition.buildWithCriterion(null, Condition.AndOr.OR,
                Condition.Criterion.builder().property("order.amount").param(Condition.Param.EQUAL).value(200.0).build());
        final Condition condition1 = Condition.buildWithCriterion(Arrays.asList(condition), Condition.AndOr.AND,
                Condition.Criterion.builder().property("order.qty").param(Condition.Param.EQUAL).value(100).build());
        final Condition condition2 = Condition.buildWithCriterion(Arrays.asList(condition1), Condition.AndOr.OR,
                Condition.Criterion.builder().property("user.code").param(Condition.Param.EQUAL).value("00001").build(),
                Condition.Criterion.builder().property("product.qty").param(Condition.Param.EQUAL).value(100).build(),
                Condition.Criterion.builder().property("order.qty").param(Condition.Param.EQUAL).value(100).build());


        log.info("JSON:[{}]", JSON.toJSONString(condition2,true));

//        log.info("condition:[{}]", condition2);
        log.info("condition:[{}]", condition.build());
        log.info("condition:[{}]", condition1.build());
        log.info("condition:[{}]", condition2.build());
//        log.info("condition:[{}]", build(condition));
//        log.info("condition:[{}]", build(condition1));
//        log.info("condition:[{}]", build(condition2));
    }

    public static String build(Condition condition) {
        String c = "";
        final StringJoiner sj = new StringJoiner(condition.getAndOr().getValue(),"(",")");
        for (Condition child : condition.getChildren()) {
            sj.add(child.build());
        }
        c = sj.toString();
        return c;
    }

}
