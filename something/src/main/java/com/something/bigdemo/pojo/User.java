package com.something.bigdemo.pojo;

import java.io.Serializable;

/**
 * @author cWww
 * @Title
 * @Description User - (对应数据库的User)
 * @date: 2018/9/3  14:10
 */
public class User implements Serializable {

    private String name;
    private String password;
    private String desc;

    public User() {
    }

    public User(String name, String password, String desc) {
        this.name = name;
        this.password = password;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
