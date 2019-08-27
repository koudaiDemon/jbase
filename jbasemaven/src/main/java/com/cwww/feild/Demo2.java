package com.cwww.feild;

import com.cwww.spring.Demo;
import com.cwww.spring.JobDemo;

import java.lang.reflect.Field;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/10  15:59
 */
public class Demo2 {

    public static void main(String[] args) throws Exception {
        JobDemo jobDemo = new JobDemo(new Demo("hello"));
        Class clazz = JobDemo.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            Object o = f.get(jobDemo);
            System.out.println(f.getName());
            System.out.println(o);
        }
    }

}
