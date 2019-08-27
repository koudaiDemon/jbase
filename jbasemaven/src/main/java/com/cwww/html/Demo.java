package com.cwww.html;

import com.cwww.feild.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/1/9  13:14
 */
public class Demo {

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);
    private static Configuration configuration = null;
    private static Template template = null;

    private static final String ENCODING = "UTF-8";

    static {
        //设置版本
        configuration = new Configuration(Configuration.VERSION_2_3_20);
        //设置编码格式
        configuration.setDefaultEncoding(ENCODING);
    }

    public static String generateHtml(final String templateFile, final Map<String, Object> param) {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(stream);
            //设置模版目录
            configuration.setDirectoryForTemplateLoading(new File("jbasemaven/src/main/resources/template"));
            //获得模版文件
            template = configuration.getTemplate(templateFile);

            template.setOutputEncoding(ENCODING);
            //将数据填充模版
            template.process(param, writer);
            writer.flush();
            return stream.toString(ENCODING);
        } catch (final Exception e) {
            LOGGER.error("Exception",e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (final IOException e) {
                    LOGGER.error("IOException",e);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
//        final Map<String,Object> param = new HashMap<>(30);
//        param.put("orderCode","1111111");
//        param.put("orderTime","1111111");
//        param.put("customerName","1111111");
//        param.put("deliveryDate","1111111");
//        param.put("deliveryFactory","1111111");
//        param.put("orderNote","1111111");
//        final List<Map<String,Object>> entryList = new ArrayList<>();
//        entryList.add(Collections.singletonMap("configurations",Collections.singletonList(Collections.singletonMap("label","aaaa"))));
//        param.put("entryList",entryList);
        User user = new User(null,null,"123456",new Date());
//
//
//
        final String html = generateHtml("productionOrder.ftl", Collections.singletonMap("user",user));
        System.out.println(html);

    }

}
