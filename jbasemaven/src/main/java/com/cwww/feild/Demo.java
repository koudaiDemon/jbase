package com.cwww.feild;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/31  11:28
 */
public class Demo {

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws Exception {

        User user = new User("0001","hello","world",new Date());
        Class clazz = User.class;

        Field[] fields = clazz.getFields();
        fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj = field.get(user);
            LOGGER.info("name:{},value:{}",field.getName(),obj);
        }
    }

}
