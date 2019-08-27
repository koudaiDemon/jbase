package com.cwww.reflect;

import java.io.Serializable;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/10/9  16:05
 */
public class Address implements Serializable {

    private String code;
    private String name;

    public Address() {
    }

    public Address(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Address{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
