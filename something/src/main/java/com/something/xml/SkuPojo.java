package com.something.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @Title SkuPojo
 * @Description ${DESCRIPTION}
 * @Author Yao.Zhang
 * @date: 2018/5/28
 */
@XmlType(propOrder = {"sellerSku","quantity","price","package_length","package_height","package_weight","package_width","images"})
public  class SkuPojo {
    private String sellerSku;
    private Integer quantity;
    private String price;
    private String package_length;
    private String package_height;
    private String package_weight;
    private String package_width;
    private List<String> images=null;

    @XmlElement(name = "SellerSku")
    public String getSellerSku() {
        return sellerSku;
    }

    public void setSellerSku(String sellerSku) {
        this.sellerSku = sellerSku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPackage_length() {
        return package_length;
    }

    public void setPackage_length(String package_length) {
        this.package_length = package_length;
    }

    public String getPackage_height() {
        return package_height;
    }

    public void setPackage_height(String package_height) {
        this.package_height = package_height;
    }

    public String getPackage_weight() {
        return package_weight;
    }

    public void setPackage_weight(String package_weight) {
        this.package_weight = package_weight;
    }

    public String getPackage_width() {
        return package_width;
    }

    public void setPackage_width(String package_width) {
        this.package_width = package_width;
    }

    @XmlElement(name = "Images")
    public List<String> getImages() {
        return this.images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}