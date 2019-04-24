package com.cwww.demo.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.cwww.demo.dto.User;
import com.cwww.zip.Demo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/26  10:35
 */
//@RestController
@Controller
@RequestMapping("/")
@EnableAsync
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    private OSSClient ossClient = new OSSClient("https://oss-cn-beijing.aliyuncs.com", "", "");

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public List<String> hello1(){
        return Collections.singletonList("hello");
    }

    @RequestMapping(value = "/helloWorld")
    public void helloWorld(HttpServletRequest httpServletRequest){

        LOGGER.info("httpServletRequest url:{}",httpServletRequest.getRequestURL());
        LOGGER.info("httpServletRequest map:{}",httpServletRequest.getParameterMap());
        httpServletRequest.getParameterMap().forEach((k,v)->LOGGER.info("key:{},value:{}",k,v));

    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @ResponseBody
    public User user(@RequestBody User user){
        LOGGER.info("user:{}",user);
        return user;
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> user(){
        final Map<String,Object> map = new HashMap<>(3);
        map.put("uid","aaaaa");
        map.put("age",16);
        map.put("married",false);
        return map;
    }

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void download(HttpServletResponse response) {
//        ZipOutputStream zipOut = null;
//        File file = new File("D:/test.zip");
        try {
            String fileName = "测试.zip";
            List<String> urls = new ArrayList<>();
            // 设置导出文件头
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            // 定义Zip输出流
//            zipOut = new ZipOutputStream(new FileOutputStream(file));
//            zipOut.setMethod(ZipOutputStream.DEFLATED);

            String bucketName = "for-test1";
//
//
            GetObjectRequest getObjectRequest1 = new GetObjectRequest(bucketName, "test/111.jpg");
            GetObjectRequest getObjectRequest2 = new GetObjectRequest(bucketName, "test/mytest01.zip");
            GetObjectRequest getObjectRequest3 = new GetObjectRequest(bucketName, "test/促销.doc");

            final OSSObject object1 = ossClient.getObject(getObjectRequest1);
            final OSSObject object2 = ossClient.getObject(getObjectRequest2);
            final OSSObject object3 = ossClient.getObject(getObjectRequest3);

            final InputStream objectContent1 = object1.getObjectContent();
            final InputStream objectContent2 = object2.getObjectContent();
            final InputStream objectContent3 = object3.getObjectContent();

            Map<String,InputStream> inputStreamMap = new HashMap<>(5);
            inputStreamMap.put("111.jpg", objectContent1);
            inputStreamMap.put("mytest01.zip", objectContent2);
            inputStreamMap.put("促销.doc", objectContent3);
            Demo.toZip(inputStreamMap,response.getOutputStream());

//            StreamUtils.copy(new FileInputStream(file),response.getOutputStream());
            response.getOutputStream().flush();
        }catch (final Exception e) {
            LOGGER.error("Exception For downUrlForZip", e);
        }
    }

    @RequestMapping(value = "/async",method = RequestMethod.GET)
    public void asyncRequest(HttpServletRequest request , HttpServletResponse response) throws IOException {
        //设置ContentType,关闭缓存
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Cache-Control","private");
        response.setHeader("Pragma","no-cache");
        final PrintWriter writer = response.getWriter();
        writer.println("老师检查作业了");
        writer.flush();
        List<String> zuoyes=new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            zuoyes.add("zuoye"+i);
        }
        //开启异步请求
        final AsyncContext ac = request.startAsync();
        doZuoye(ac, zuoyes);
        writer.println("老师布置作业");
        writer.flush();

    }

    @RequestMapping(value = "/mvcAsync", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<String> mvcAsyncTask() throws Exception{
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setIoThreadCount(1).build();
        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        PoolingNHttpClientConnectionManager conManager = new PoolingNHttpClientConnectionManager(ioReactor);
        conManager.setMaxTotal(100);
        conManager.setDefaultMaxPerRoute(100);
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setConnectionManager(conManager).build();
        // Start the client
        httpclient.start();
        //设置超时时间200ms
        final DeferredResult<String> deferredResult = new DeferredResult<String>(2000L);
        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                System.out.println("异步调用执行超时！thread id is : " + Thread.currentThread().getId());
                deferredResult.setResult("超时了");
            }
        });
        System.out.println("/asynctask 调用！thread id is : " + Thread.currentThread().getId());
        final HttpGet request2 = new HttpGet("http://www.apache.org/");
        httpclient.execute(request2, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(final HttpResponse response2) {
                System.out.println(request2.getRequestLine() + "->" + response2.getStatusLine());
                deferredResult.setResult(request2.getRequestLine() + "->" + response2.getStatusLine());
            }

            @Override
            public void failed(final Exception ex) {
                System.out.println(request2.getRequestLine() + "->" + ex);
            }

            @Override
            public void cancelled() {
                System.out.println(request2.getRequestLine() + " cancelled");
            }

        });
        return deferredResult;
    }

    private void doZuoye(final AsyncContext ac, final List<String> zuoyes) {
        ac.setTimeout(1*60*60*1000L);
        ac.start(new Runnable() {
            @Override
            public void run() {
                //通过response获得字符输出流
                try {
                    PrintWriter writer=ac.getResponse().getWriter();
                    for (String zuoye:zuoyes) {
                        writer.println("\""+zuoye+"\"请求处理中");
                        Thread.sleep(1*1000L);
                        writer.flush();
                    }
                    ac.complete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
