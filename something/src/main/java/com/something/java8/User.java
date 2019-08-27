package com.something.java8;

import java.io.Serializable;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/11/6  18:35
 */
public class User implements Serializable {

    private String username;
    private String password;
    private String addr;

    public User(String username, String password, String addr) {
        this.username = username;
        this.password = password;
        this.addr = addr;
    }

    public User() {
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
