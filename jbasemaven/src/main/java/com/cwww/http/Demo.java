package com.cwww.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.net.ConnectException;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/13  10:42
 */
public class Demo {

    public static void main(String[] args) throws Exception {

        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod("");
        getMethod.getParams().setParameter("http.socket.timeout", Integer.valueOf(100000));
//        final StringRequestEntity e = new StringRequestEntity(request, contentType, "UTF-8");
//        postMethod.setRequestEntity(e);

        getMethod.setRequestHeader("Accept","text/html;charset=utf-8");
        int statusCode = 0;
        try {

            statusCode = httpClient.executeMethod(getMethod);
        } catch (ConnectException e){
            System.out.println("出现异常");
        }
        System.out.println(statusCode);
//        int status = HttpStatus.SC_OK;
        final String requestData = getMethod.getResponseBodyAsString();
        System.out.println(requestData);
        getMethod.releaseConnection();

    }

}
