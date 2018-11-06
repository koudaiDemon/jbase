package com.something.xml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;

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

        RequestReceiveDatas requestReceiveDatas = new RequestReceiveDatas();

        RequestReceiveData requestReceiveData = new RequestReceiveData();
        requestReceiveData.setCode("asdasd");
        requestReceiveData.setPackages(12.0);
        requestReceiveDatas.setRequestReceiveDataList(Collections.singletonList(requestReceiveData));

        final StringWriter writer = new StringWriter();
        JAXBContext.newInstance(RequestPojo.class);
//        Map<String,Object> properties = new HashMap();


        final JAXBContext context = JAXBContextFactory.createContext(new Class[]{RequestReceiveDatas.class,RequestReceiveData.class},new HashMap());
        final Marshaller m = context.createMarshaller();
        final Unmarshaller unmarshaller = context.createUnmarshaller();
//        unmarshaller.setProperty();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //防止文件中文乱码
        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        m.marshal(requestReceiveDatas, writer);
        final String xml = writer.toString();
        System.out.println(xml);

        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<requestReceiveDatas>\n" +
                "   <datas>\n" +
                "      <Code>asdasd</Code>\n" +
                "   </datas>\n" +
                "</requestReceiveDatas>";

        str = "{\n" +
                "\t\"datas\":\n" +
                "\t\n" +
                "\t\t{\n" +
                "\t\t\"Code\":\"1231\",\n" +
                "\t\t\"signDate\":\"123123\",\n" +
                "\t\t\"package\":\"123\"\n" +
                "\t\t}\n" +
                "\t\n" +
                "}";
        System.out.println(StringEscapeUtils.unescapeJava(str));
        final StringReader stringReader = new StringReader(StringEscapeUtils.unescapeJava(str));

        final Source source = new StreamSource(stringReader);
        unmarshaller.setProperty(JAXBContextProperties.MEDIA_TYPE, "application/json");
        unmarshaller.setProperty(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
//        unmarshaller.setProperty(JAXBContextProperties);
        JAXBElement<RequestReceiveDatas> unmarshal = unmarshaller.unmarshal(source, RequestReceiveDatas.class);


        System.out.println(unmarshal.getValue());
        System.out.println(unmarshal);
//        String json = "{\"name\":\"abc\"}";
//
//        JSONArray objects = JSON.parseArray(json);
//
//        System.out.println(objects);


        System.out.println("Ⅰ");
    }

}
