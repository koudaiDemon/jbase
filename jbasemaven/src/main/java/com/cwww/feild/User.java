package com.cwww.feild;

import java.util.Date;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/31  11:29
 */
public class User {

    private String uid;
    private String username;
    private String password;
    private Date birthday;

    public User() {
    }

    public User(String uid, String username, String password, Date birthday) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
