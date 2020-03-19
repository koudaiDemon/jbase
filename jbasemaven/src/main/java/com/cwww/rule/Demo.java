package com.cwww.rule;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

        final Rule rule = new Rule();
        rule.setCode("test");
        rule.setAction("action");
        final Condition condition = Condition.buildWithCriterion(null, Condition.Param.OR, "条件A");
        final Condition condition1 = Condition.buildWithCriterion(condition, Condition.Param.AND, "条件B");
        final Condition condition2 = Condition.buildWithCriterion(condition1, Condition.Param.OR, "条件C", "条件D", "条件E");


        log.info("JSON:[{}]", JSON.toJSONString(condition2,true));

        log.info("condition:[{}]", condition2);
        log.info("condition:[{}]", condition.build());
        log.info("condition:[{}]", condition1.build());
        log.info("condition:[{}]", condition2.build());
        log.info("condition:[{}]", build(condition));
        log.info("condition:[{}]", build(condition1));
        log.info("condition:[{}]", build(condition2));
    }

    public static String build(Condition condition) {
        String c = "";
        while (condition != null) {
            final Condition parent = condition.getParent();
            if (null == parent) {
                return StringUtils.isEmpty(c) ? condition.build() : c;
            }
            final StringJoiner sj = new StringJoiner("","(",")");
            sj.add(StringUtils.isEmpty(c) ? condition.build() : c);
            sj.add(parent.getParam().getValue());
            sj.add(parent.build());
            c = sj.toString();
            condition = parent;
        }
        return c;
    }

}
