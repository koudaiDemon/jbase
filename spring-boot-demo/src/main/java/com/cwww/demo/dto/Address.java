package com.cwww.demo.dto;

import java.io.Serializable;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/10/16  12:13
 */
public class Address implements Serializable {

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
