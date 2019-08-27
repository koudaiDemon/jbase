package com.something.java8;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/14  12:27
 */
public class Product {

    private String code;
    private Product baseProduct;

    public Product(String code, Product baseProduct) {
        this.code = code;
        this.baseProduct = baseProduct;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Product getBaseProduct() {
        return baseProduct;
    }

    public void setBaseProduct(Product baseProduct) {
        this.baseProduct = baseProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", baseProduct=" + baseProduct +
                '}';
    }
}
