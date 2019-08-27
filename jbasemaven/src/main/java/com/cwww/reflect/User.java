package com.cwww.reflect;

/**
 * @author ShiYuying
 * @Title
 * @Description
 * @date 2018/8/31 23:05
 */
public class User {

    public String name;
    protected int age;
    private String phoneNum;
    char sex;


    public User() { }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", sex=" + sex
                + ", phoneNum=" + phoneNum + "]";
    }

}