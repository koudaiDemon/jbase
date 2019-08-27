package com.something.jvm;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cWww
 * @Title MemoryDemo
 * @Description 内存测试
 * @date: 2019/5/29  17:01
 */
@Slf4j
public class MemoryDemo {

    public static void main(String[] args) {

//        final List<String> list = new ArrayList<>();
//
//        while (true) {
//            list.addAll(Arrays.asList("aaa","bbb","ccc"));
//            list.addAll(list);
//        }
        while (true) {
            demo2();
//            demo();
        }

    }

    private static void demo(){
        final GetMethod getMethod = new GetMethod("http://www.baidu.com");
        try {
            final HttpClient httpClient = new HttpClient();
            httpClient.executeMethod(getMethod);
            final String responseBodyAsString = getMethod.getResponseBodyAsString();
            log.info("response:{}",responseBodyAsString);
        } catch (Exception e) {
            log.error("ex",e);
        } finally {
            getMethod.releaseConnection();
        }
    }

    private static void demo2(){
        // 创建文档及设置根元素节点的方式
        Element root = DocumentHelper.createElement("Envelope");
        Document document = DocumentHelper.createDocument(root);
        Element headerElement = root.addElement("Header");
        // header部分
        headerElement.addElement("SourceID").addText("1");
        headerElement.addElement("MessageID").addText("1");
        headerElement.addElement("MessageType").addText("Orders");
        // 生成xml格式
        OutputFormat format = new OutputFormat();
        // 设置编码格式
        format.setEncoding("UTF-8");
        format.setNewlines(true);
        format.setIndent("    ");
        StringWriter writerStr = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter(writerStr, format);
        try {
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException e) {
            log.error("io",e);
        }
        String strXml = writerStr.getBuffer().toString();
        log.info("str:{}",strXml);
    }

}
