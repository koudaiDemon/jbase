package com.something.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title RequestPojo
 * @Description ${DESCRIPTION}
 * @Author Yao.Zhang
 * @date: 2018/5/28
 */
@XmlRootElement(name = "Request")
public class RequestPojo {

    private ProductPojo product;

    @XmlElement(name = "Product")
    public ProductPojo getProduct() {
        return product;
    }

    public void setProduct(ProductPojo product) {
        this.product = product;
    }

}
