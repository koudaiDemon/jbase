package com.something.java8;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/11/6  18:47
 */
public class SampleUser implements Serializable {

    private String username;
    private String password;

    public SampleUser() {
    }

    public SampleUser(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SampleUser that = (SampleUser) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "SampleUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
