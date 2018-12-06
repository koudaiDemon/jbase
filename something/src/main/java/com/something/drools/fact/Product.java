package com.something.drools.fact;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/11/6  20:21
 */
public class Product {

    public static final String DIAMOND = "DIAMOND";

    public static final String GOLD = "GOLD";

    private String type;

    private int discount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
