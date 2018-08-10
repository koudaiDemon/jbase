package com.cwww.demo.dto;

import java.io.Serializable;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/10  10:48
 */
public class User implements Serializable {

    private String name;
    private String nickname;
    private String country;

    public User() {
    }

    public User(String name, String nickname, String country) {
        this.name = name;
        this.nickname = nickname;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
