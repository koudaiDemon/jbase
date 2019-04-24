package com.cwww.spring.ListDemo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author cWww
 * @Title ListDemo
 * @Description ListDemo
 * @date: 2019/4/11  19:51
 */
public class ListDemo {
    private String hello;
    private Map<String,ActionDemo> map;


    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public Map<String, ActionDemo> getMap() {
        return map;
    }

    @Autowired
    public void setMap(Collection<? extends ActionDemo> demos) {
        this.map = new HashMap<>();
        final Iterator<? extends ActionDemo> iterator = demos.iterator();

        while(iterator.hasNext()) {
            ActionDemo demo = iterator.next();
            this.map.put(demo.getString(), demo);
        }
    }
}
