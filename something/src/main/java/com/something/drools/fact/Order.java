package com.something.drools.fact;

import java.util.Date;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/11/6  19:55
 */
public class Order {
    private String code;
    private String product;
    private User user;
    private Double totalPrice;
    private Date bookingDate;

    public Order() {
    }

    public Order(String code, String product, User user, Double totalPrice, Date bookingDate) {
        this.code = code;
        this.product = product;
        this.user = user;
        this.totalPrice = totalPrice;
        this.bookingDate = bookingDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
