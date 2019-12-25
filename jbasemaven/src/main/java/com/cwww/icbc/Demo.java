package com.cwww.icbc;



import cn.com.infosec.icbc.ReturnValue;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/10/16  19:38
 */
public class Demo {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();

        List<NameValuePair> form = new ArrayList<>();

        HttpPost postMethod = new HttpPost("https://myipad.dccnet.com.cn/servlet/ICBCINBSEBusinessServlet");

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setConnectionRequestTimeout(5000)
                .build();

        postMethod.setConfig(requestConfig);

//        ReturnValue.base64dec();

        String data = "B2B";

//        System.out.println(iso2utf(data));
        data = iso2utf(data);

        String password = "95588";
//        System.out.println(iso2utf(password));
        password = iso2utf(password);

        //1.组装数据
        final String str = "APIName=B2B&APIVersion=001.001.001.001&Shop_code=1104EE20386013&MerchantURL=http://www.merchant.com/ICBCPay/&ContractNo=test2018102201&ContractAmt=1&Account_cur=001&JoinFlag=2&SendType=0&TranTime=20181022180000&Shop_acc_num=1104021009000097469&PayeeAcct=1104021009000097469";


        //商城私钥文件(.key)
        //2.数据签名
        FileInputStream f = new FileInputStream("D:\\yuwell\\yyyl.key");
        byte[] bs = new byte[f.available()];
        f.read(bs);
        f.close();
//        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDGWMsErdfKFsVWU04EDFyn89146wlwgF1M455tkUWsSdBU+fW+qoY1Cw7To59SsLVKOjaD3x4UYWOq2sxbHPtHdJIcr+T2go17Yo+9cWs9xrVBQf/YGrwxA+7fpUX9Rdnd3AJdiKhzYeqHCHpjLRglrhkFiskXhBxTz/eaPzDcwRMrunRO3PyuHwSuRdy/Zplj/AWzh1Yu/f48Curvg4pyPA7XsL1ulKVuO0wBO87G/9rZvuTgy+sSwFKrpGPeH4CNDzMjzjFF6PI8TNyZjH27kp+xiD6D/xtiVuMxkIgGO/Tz6JXFZpWQiTzuLnOMwm/q6/CvwpbYksWDAhwtMJG7AgMBAAECggEBAKquhiXEo0kYR0B990isip9Mz2zadQkGrUMZLKAEvvv/mPNHFh25CDxlA6FD0DqMLbVuWrMoMO5A0E+acPiG/AUFXyaohlNLDQz8R80x6KMEd71sWglRGFjllY2G36+PiGfOh4qNqsd/nx4MQaVOjNT/+DA4ucsuuGG3OM7XSejsB9b9HwsOswgVxlDqKkZDZbA2g1MNhTm12tgSp6ob43/s3RoEtqWHX9Ny/aS/GZO3EDXUsks20qZ7a+ZwDVS+dd2o44QyiGSyAy/7E/qZOeLr1QJt7x20K9gRjiXpLi9gu6ZVj6IzYCpF3onmvWu8yU4cCWjpM6fNhCsPyXB9PkECgYEA893C3O9QUktXxv71FktV6d7koGk0QnL34OYx/sfKPHnHRGt4SsYROjhiu0c1muA3TMWHqyTSbBdTDxlVTZB2JVbqecozJLvdcbcu68Og5G3IvXQkygbvU1Svk/SlZTFGrJ3NjOrB3UJnw9BUokXVRgbHE1mxvH4SawWugtL7UbMCgYEA0Dc61jcDsgkdId+vNxAn8gOFFgtxIE2Fy1inqbIoQbJwoMZhqXcLLMXNcra8AFu2sEPf42WKa1R6opSPh0rwW5L4JHknXbS4y8XkWI+OJBReDDo3PCXkQFgu2AT6lmaRCln8W6JlhZDwwNtxmPpmxs/NPVRiLU9LAeW7OHaz69kCgYAFeUHJcbmszzIeLlTT9kbsY+tfyly47RmxO5l9rYBVkSou1/awPvfeBav5piSG4Gd9WqWr6ryqXm+n1Bq9DfQI7IWAzFAPkAPtbH4qHo768Zfi4Lt+qV8v0KPMP16DAfMjJruZYR+O1tG7bG6p4cVit/456fZ6U54YNt1u5EttzwKBgDvk50Pfp2x7OqoX0isgtpzjblEdEXmdUzNfWTtzSCk9zzAwYL+CwvAqJiMtdX8U4sRpgenDt8GQF4bU/USJxgJRkJfNUgmehtn3HuxqvxSZ10PlKyZeL/5iX3c8V2kUL/yuTe1+FX4KZ3bSqSa8QPxHol5V+7fU61qsXuKrIq5hAoGAKfyiXQ/it6sC717Ywz23csza3TairoOwsOLRlS2vipUsuVdXa0e7jRmE9A8s3qq2BgxuMACYf/SidJ6Cmrju6CLsbIhvCv7KlJsa3rmNthvE58wiy9aXKiOVqsDZimRxxiDcK/2oWsJNTQLg8x3lCLZjkn2/RTQi6IEeLyIEIBY=";
//        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxljLBK3XyhbFVlNOBAxcp/PdeOsJcIBdTOOebZFFrEnQVPn1vqqGNQsO06OfUrC1Sjo2g98eFGFjqtrMWxz7R3SSHK/k9oKNe2KPvXFrPca1QUH/2Bq8MQPu36VF/UXZ3dwCXYioc2Hqhwh6Yy0YJa4ZBYrJF4QcU8/3mj8w3METK7p0Ttz8rh8ErkXcv2aZY/wFs4dWLv3+PArq74OKcjwO17C9bpSlbjtMATvOxv/a2b7k4MvrEsBSq6Rj3h+AjQ8zI84xRejyPEzcmYx9u5KfsYg+g/8bYlbjMZCIBjv08+iVxWaVkIk87i5zjMJv6uvwr8KW2JLFgwIcLTCRuwIDAQAB";
//        byte[] bs = privateKey.getBytes();
        System.out.println(new String(bs));
        final byte[] sign = ReturnValue.sign(str.getBytes(), str.getBytes().length, bs, password.toCharArray());

        //3.BASE64编码
        byte[] signature = ReturnValue.base64enc(sign);
        String signatures = transforToBASE64(sign);
        System.out.println("=========================BASE64编码");
        System.out.println(signatures);


        //4.证书base64编码
        String certs = "";
        FileInputStream fs = new FileInputStream("D:\\yuwell\\测试公钥ebb2cpublic.crt");
        byte[] bsc = new byte[fs.available()];
        fs.read(bsc);
        fs.close();
        byte[] cert = ReturnValue.base64enc(bsc);
//        cert = getFromBASE64(fs);
//        certs = new String(cert);
        certs = transforToBASE64(bsc);
        System.out.println("=========================4.证书base64编码");
        System.out.println(certs);

//        String tranDataBase64 = "";
//        byte[] base64 = ReturnValue.base64enc(data.getBytes());
//        tranDataBase64 = new String(base64);
//        System.out.println("=========================");
//        System.out.println(tranDataBase64);

//        form.add(new BasicNameValuePair("APIName",transforToBASE64(ReturnValue.sign("B2B".getBytes(),"B2B".getBytes().length,bs,password.toCharArray()))));
//        form.add(new BasicNameValuePair("APIVersion","001.001.001.001"));
//        form.add(new BasicNameValuePair("Shop_code","1104EE20386013"));
//        form.add(new BasicNameValuePair("MerchantURL","http://www.merchant.com/ICBCPay/"));
//        form.add(new BasicNameValuePair("ContractNo","test2018102201"));
//        form.add(new BasicNameValuePair("ContractAmt","1"));
//        form.add(new BasicNameValuePair("Account_cur","001"));
//        form.add(new BasicNameValuePair("JoinFlag","2"));
//        form.add(new BasicNameValuePair("Mer_Icbc20_signstr",signatures));
//
//        form.add(new BasicNameValuePair("Cert",certs));
//        form.add(new BasicNameValuePair("SendType","0"));
//        form.add(new BasicNameValuePair("TranTime","20181022180000"));
//        form.add(new BasicNameValuePair("Shop_acc_num","1104021009000097469"));
//        form.add(new BasicNameValuePair("PayeeAcct","1104021009000097469"));
//        HttpEntity httpEntity = new UrlEncodedFormEntity(form,"GBK");
//        postMethod.setEntity(httpEntity);
//        try {
//
//            HttpResponse execute = client.execute(postMethod);
//            System.out.println(execute.getStatusLine().getStatusCode());
//            String s = EntityUtils.toString(execute.getEntity(), "UTF-8");
//            System.out.println(s);
//            client.close();
//        } catch (ConnectException e){
//            System.out.println("出现异常");
//        }
//        System.out.println();
    }

    public static byte[] getFromBASE64(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        } else {
            BASE64Decoder decoder = new BASE64Decoder();

            try {
                return decoder.decodeBuffer(inputStream);
            } catch (Exception var3) {
                return null;
            }
        }
    }

    public static String transforToBASE64(byte[] bytes) {
        if (bytes == null) {
            return null;
        } else {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            try {
                return base64Encoder.encode(bytes);
            } catch (Exception var3) {
                return null;
            }
        }
    }

    /**
     * 从 ISO8859_1 转换到 UTF
     *
     * @param str
     *            需要转换字符集的字符串
     * @return 转换后的字符串
     */
    public static String iso2utf(String str) {
        String value = "";
        if (str == null || str.length() == 0) {
            return "";
        }

        try {
            value = new String(str.getBytes("ISO8859_1"), "UTF-8");
        } catch (Exception e) {
            return null;
        }
        return value;
    }

}
