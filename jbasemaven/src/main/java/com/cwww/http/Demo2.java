package com.cwww.http;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/5/16  10:19
 */
public class Demo2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Demo2.class);

    private static final Integer DEFAULT_CONNECT_TIMEOUT = 1000 * 60 * 2;
    private static final Integer DEFAULT_SOCKET_TIMEOUT = 1000 * 60 * 2;
    private static final String QUERY_SYMBOL = "?";
    private static final String DEFAULT_CHARSET = "utf-8";

    /**
     * http get请求
     * @param url 请求路径
     * @param obj 请求参数
     * @return 返回信息
     */
    public static Map<String,String> get(String url, Map<String,Object> obj) throws HttpException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("***********request parameter************{}",JSON.toJSONString(obj));
        }
        final Map<String,String> responseMap = new HashMap<>(15);


        String query = "";
        try {
            if (MapUtils.isNotEmpty(obj)) {
                query = buildQuery(obj, DEFAULT_CHARSET);
            }
        } catch (IOException e) {
            throw new HttpException("Request para");
        }
        if (url.endsWith(QUERY_SYMBOL)) {
            url = url + query;
        } else {
            url = url + QUERY_SYMBOL + query;
        }
        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpGet get = new HttpGet(url);
        final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).setConnectTimeout(DEFAULT_CONNECT_TIMEOUT).build();
        get.setConfig(requestConfig);
        // 响应内容
        get.addHeader("Accept","text/plain;charset=utf-8");
        try (CloseableHttpResponse response = client.execute(get)) {
            final HttpEntity entity = response.getEntity();
            if (HttpStatus.OK.value() == response.getStatusLine().getStatusCode()) {
                responseMap.put("content", EntityUtils.toString(entity, "UTF-8"));
                final Header[] allHeaders = response.getAllHeaders();
                for (Header header :allHeaders) {
                    responseMap.put(header.getName(), header.getValue());
                }
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("======responseData======={}",responseMap);
                }
            } else {
                LOGGER.error("Response StatusLine:{}", response.getStatusLine());
                throw new HttpException("Server Error:" + EntityUtils.toString(entity, "UTF-8"));
            }
        } catch (Exception e) {
            LOGGER.error("interfaceError",e);
            throw new HttpException("Server Error", e);
        } finally {
            try {
                if (null != client) {
                    client.close();
                }
            } catch (IOException e) {
                LOGGER.error("CLOSE EXCEPTION",e);
            }
        }
        return responseMap;
    }

    /**
     * 将map转换为请求字符串
     * <p>data=xxx&msg_type=xxx</p>
     *
     * @param params
     * @param charset
     * @return
     * @throws IOException
     */
    public static String buildQuery(Map<String, Object> params, String charset) throws IOException {
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuffer data = new StringBuffer();
        boolean flag = false;

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (flag) {
                data.append("&");
            } else {
                flag = true;
            }
            data.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(), charset));
        }
        return data.toString();

    }


    public static void main(String[] args) throws Exception {


        final Map<String, String> map = Demo2.get("https://www.baidu.com", null);

        System.out.println(map);


    }

}
