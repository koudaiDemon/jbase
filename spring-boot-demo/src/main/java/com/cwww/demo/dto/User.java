package com.cwww.demo.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
    private Map<String,String> local;
    private List<Address> addresses;
    private Integer age;
    private Double weight;
    private Phone phone;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

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

    public Map<String, String> getLocal() {
        return local;
    }

    public void setLocal(Map<String, String> local) {
        this.local = local;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", country='" + country + '\'' +
                ", local=" + local +
                '}';
    }

    public static class Phone implements Serializable {
        private String phone;
        private String mobile;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
