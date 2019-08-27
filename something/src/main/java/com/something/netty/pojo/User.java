package com.something.netty.pojo;

import java.io.Serializable;

/**
 * @author cWww
 * @Title pojo
 * @Description user pojo
 * @date: 2018/9/29  19:33
 */
public class User implements Serializable {

    private String uid;
    private String name;
    private String password;

    public User() {
    }

    public User(String uid, String name, String password) {
        this.uid = uid;
        this.name = name;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
}
