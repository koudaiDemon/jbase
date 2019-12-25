package com.cwww.spring.autowired;

import com.cwww.spring.ListDemo.ActionDemo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/5/8  15:35
 */
public class Demo {
    @Autowired
    private Map<String,ActionDemo> actionDemoMap;

    public Map<String, ActionDemo> getActionDemoMap() {
        return actionDemoMap;
    }

    public void setActionDemoMap(Map<String, ActionDemo> actionDemoMap) {
        this.actionDemoMap = actionDemoMap;
    }
}
