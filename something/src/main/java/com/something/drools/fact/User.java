package com.something.drools.fact;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/11/6  19:54
 */
public class User {
    private String uid;
    private String username;
    private String password;
    private Integer age;
    private String addr;

    public User() {
    }

    public User(String uid, String username, String password, Integer age, String addr) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.age = age;
        this.addr = addr;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
