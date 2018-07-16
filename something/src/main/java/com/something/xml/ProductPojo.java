package com.something.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * @Title ProductPojo
 * @Description ${DESCRIPTION}
 * @Author Yao.Zhang
 * @date: 2018/5/28
 */
public class ProductPojo {

    private List<SkuPojo> skus;

    @XmlElementWrapper(name = "Skus")
    @XmlElement(name="Sku")
    public List<SkuPojo> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuPojo> skus) {
        this.skus = skus;
    }

}