package com.cwww.reflect;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * @author ShiYuying
 * @Title
 * @Description
 * @date 2018/8/31 22:59
 */
public class Constructors {

    public static void main(String[] args) throws Exception {
        //1.加载Class对象
        Class studentClass = Student.class;

        //2.获取所有公有构造方法
        System.out.println("所有公有构造方法:");
        Constructor[] conArray = studentClass.getConstructors();
        Arrays.stream(conArray).forEach(con->System.out.println(con));


        System.out.println("所有的构造方法(包括：私有、受保护、默认、公有):");
        conArray = studentClass.getDeclaredConstructors();
        Arrays.stream(conArray).forEach(con->System.out.println(con));


        System.out.println("获取公有、无参的构造方法:");
        Constructor con = studentClass.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。

        System.out.println("con = " + con);
        //调用构造方法
        Object obj = con.newInstance();
        //	System.out.println("obj = " + obj);
        //	Student stu = (Student)obj;

        System.out.println("获取私有构造方法，并调用:");
        con = studentClass.getDeclaredConstructor(char.class);
        System.out.println(con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con.newInstance('女');
    }

}