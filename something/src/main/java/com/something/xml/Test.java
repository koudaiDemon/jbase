package com.something.xml;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cWww
 * @Title
 * @Description JAXB实现xml文件的创建和解析
 * @date: 2018/6/8  11:20
 */
public class Test {

    public static void main(String[] args) throws Exception {

        final List<SkuPojo> skusList = new ArrayList<>();
        SkuPojo sku = new SkuPojo();
        sku.setSellerSku("123231232131");
        sku.setQuantity(100);
        skusList.add(sku);
        sku = new SkuPojo();
        sku.setSellerSku("222222222222");
        sku.setQuantity(66);
        skusList.add(sku);
        ProductPojo productPojo = new ProductPojo();
        productPojo.setSkus(skusList);
        RequestPojo requestPojo = new RequestPojo();
        requestPojo.setProduct(productPojo);

        System.out.println(JSON.toJSONString(requestPojo));

//        final StringWriter writer = new StringWriter();
//        final JAXBContext context = JAXBContext.newInstance(RequestPojo.class);
//        final Marshaller m = context.createMarshaller();
//        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        //防止文件中文乱码
//        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//        m.marshal(requestPojo, writer);
//        final String xml = writer.toString();
//        System.out.println(xml);
    }

}
