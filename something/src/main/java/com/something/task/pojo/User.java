package com.something.task.pojo;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/4  11:33
 */
public class User extends Item {

    private String uid;
    private String username;
    private String address;

    public User(String uid, String username, String address) {
        this.uid = uid;
        this.username = username;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
