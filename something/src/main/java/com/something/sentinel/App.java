package com.something.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cWww
 * @Title App
 * @Description 应用
 * @date: 2019/4/24  10:51
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        // 配置规则.
        initFlowRules();
        while (true) {
            int time = (int)(Math.random()*50 + 1);
            System.out.println("准备执行:"+time+"次");
            for (int i = 0 ; i < time ; i++) {
                // 1.5.0 版本开始可以直接利用 try-with-resources 特性
                try (Entry entry = SphU.entry("HelloWorld")) {
                    // 被保护的逻辑
                    System.out.println("hello world");
                } catch (BlockException ex) {
                    // 处理被流控的逻辑
                    System.out.println("blocked!");
                }
            }
            try {
                System.out.println("执行结束");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

}
