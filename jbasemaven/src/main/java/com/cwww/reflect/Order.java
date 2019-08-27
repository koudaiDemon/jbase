package com.cwww.reflect;

import java.io.Serializable;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/10/9  16:03
 */
public class Order implements Serializable {

    private String code;
    private String detail;
    private Address address;

    public Order() {
    }

    public Order(String code, String detail, Address address) {
        this.code = code;
        this.detail = detail;
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "code='" + code + '\'' +
                ", detail='" + detail + '\'' +
                ", address=" + address +
                '}';
    }
}
