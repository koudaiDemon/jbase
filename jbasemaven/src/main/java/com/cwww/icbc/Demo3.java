package com.cwww.icbc;

import cn.com.infosec.icbc.ReturnValue;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.EbankcSignInfoQueryRequestV1;
import com.icbc.api.request.EnterpriseOpenpayDirectPayRequestV1;
import com.icbc.api.response.EbankcSignInfoQueryResponseV1;
import com.icbc.api.response.EnterpriseOpenpayDirectPayResponseV1;
import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/11/8  13:24
 */
public class Demo3 {

    protected static final String MY_PRIVATE_KEY = "BKiCCIc3wYRTIozUR76oYguDToWX+jc4Aak7i5LWkw+lq9rl41fKmZrdWLsSO9n5VnaSgjVAqo7a\n" +
            "8qRJGaf3552RV22ZzRyCt2GQzi4yVX1zqoAlQCAhTMiPK4ytS8MBP5dMnXAORnnUTZqESeOvAfW3\n" +
            "kMDt03rSE+q1ikVDTXYRlO+rBBqb6ipNuKkrbxQUYcDRF9pp6wHnP/KNqdWgmkCc3ltHxwYw1q2o\n" +
            "LJ5qGGrjXU+VttIQY+0/FmS7yTgjL8UvgGStOJdqURm3ZHo7AcNtKDM4iLySTSkmn5imrh0z2sOF\n" +
            "kMWifaTjcje9WAytUsQMNpRqOmUoqW0glIr9jXiQoxyXfd+uKbUVt9+PXla7GK7KDtv4cPI6TMx4\n" +
            "peZwPnUGVT3y43f0ahoBnY5a72k1ewuNGcx7Ll8sftbKJRsNa+0axXBuHXhKuKkcD+Hj2YTfakMM\n" +
            "nqjAQuGaZ320jVQYUfkN05Q6QpfiO1eWyVbMdhqisLD+V0PMEvNY7gSwGaqqZy6Y/oW9MqELUWsE\n" +
            "Lu7JDXaa7KgRrAZdxE9/2R8rmHQGhQwbqcWrYWQDsKbTetSWU5vJPZAqfswHCeku15KRPEoQL5w1\n" +
            "XC+WI1EyN4viD8soF4mFlVwALK56YZ43IwpTka50hrTrR1w+/bybzLCqPrv/ZXam1ylookQbq9Iu\n" +
            "IIsBHEa3E7z04ehCBfM530WRQZHKOIO8ugUuaZbMlMbTlDTKJIocC6c9DWCMwwAWGWj0UwAs9+VC\n" +
            "/NjBoornxT3/O5IQh3txBT/T+wo6VvTtuoXSp3I7Vd+HK2EObKFTHUTOrxQczLuAX2ieNQA8CuYJ\n" +
            "17vAX18R8VH5k7XxgebuT7ek4t+0m4MXJR4EuJOW1Yvpv6d+wbf01UDOuUn5/yw3KTvFTp/9/8jl\n" +
            "nqld7lAePYd9uQzuuWFlgmEa0qYKhlmnCiSylZV0JsnIRIYIYfiYoZBPx3mBRRlSnzW6mCBzD09Q\n" +
            "CN5hB6z5RwcfE6Q76CTGsiHhCYN0xFAb26bp4L1JgGHI48E4eCHLYBa8s/vyxhKdv3KqIlvyleMo\n" +
            "wQRKAVzbQRTiqtPzEXJwLt3ku2B09VbUPntohU5fWAEGkcFTVQ4RpMXvxHMM5VJdoJ6PE8E6tnVX\n" +
            "ezjyE6ZHEFsCuVfT3YsAbip51aU2nQjMV76VLkwhJdI/C9wO7uoWPDl80ihum8JwsuHHdZnv/Oak\n" +
            "25GWllJktDeC5oJBioLzpm+hh7BMJsApTiVESU9O30c2A9qVBG1P4ydoTliRxQWjj67ax9HCwK+/\n" +
            "ZbQsj4ypXrjz1gOc6uuKdnbr+kNxmfy9kxG/IaIn0cRuDgWxGi7sBddpYW0ZjvZDAIQF5IZzznnd\n" +
            "XTN8ptT+3AS8OzbES9Hll/cO6WnH9agzkyCXR3P49+fT77AKpSuptxwBxoPHC7ODjoLJG8nhOEO7\n" +
            "rHld7XSoAFkFSCMS2JxWD8McyevfqA5jm2NnAXsgmmHo2yWiYPfSzGabPQmmgYhjNUWDDxHz7WcL\n" +
            "kG8Sfa74cfatJRftiXho/ftxmRYnGeXxOsWt1UxQJ8KQxrMWhJfE7qf7MrvtONWTgo6yFpwo";

    protected static final String APIGW_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "1104EH0012001";

    protected static final String PASSWORD = "95588";

    protected static final String MY_PUB_KEY = "MIIDmDCCAoCgAwIBAgIKG5LKECVWAAK/OTANBgkqhkiG9w0BAQsFADA7MR8wHQYDVQQDExZJQ0JD\n" +
            "IFRlc3QgQ29ycG9yYXRlIENBMRgwFgYDVQQKEw90ZXN0aWNiYy5jb20uY24wHhcNMTgxMTI2MDQz\n" +
            "NjU4WhcNMTkxMTI2MDQzNjU4WjBIMR0wGwYDVQQDDBQxMTA0RUgwMDEyMDAxLmUuMTEwNDENMAsG\n" +
            "A1UECwwEMTEwNDEYMBYGA1UECgwPdGVzdGljYmMuY29tLmNuMIIBIjANBgkqhkiG9w0BAQEFAAOC\n" +
            "AQ8AMIIBCgKCAQEAoQb+ty4E+RBr1sy5r8jv3/1uHzRsOlBLUtj277u5p2AvOM+WASeUtmUsJOYs\n" +
            "IIqG1+zoFjMr6MfuYq98fPz5Zlb7t1xZeXHpJjKUE9qeuyeuzrFU+j0zwtTZhlacZCW75V0UjhZY\n" +
            "rabC3e1j0lb59qWCn5NeSbLS1fqWq9PhIOOXh/FSV0E4idxGosxKcbh1iZuvK1hRRzuT2jZqV2jc\n" +
            "Vyye5vZ8Ij/5VDuOG0qitlTFzu7gPNAaHbuwi/+Rnm9dVdL6ZDrfyVjHUtoDrdNDECDQZuOVF9uV\n" +
            "Dqh32+lGcOYg/XAWSDLwbhoPto9arZO3dxRnEi8J0tQLt1DIk11l8QIDAQABo4GQMIGNMB8GA1Ud\n" +
            "IwQYMBaAFER9t5AsN6TZ7WzipIdXZwq18E0UMEsGA1UdHwREMEIwQKA+oDykOjA4MQ4wDAYDVQQD\n" +
            "DAVjcmwzNzEMMAoGA1UECwwDY3JsMRgwFgYDVQQKDA90ZXN0aWNiYy5jb20uY24wHQYDVR0OBBYE\n" +
            "FFn30zYBJFkNmFzVuy8lCtKdeVk7MA0GCSqGSIb3DQEBCwUAA4IBAQAVXTh5E/78huol1+kThMOr\n" +
            "wW4sOiSNhho7SZtQHOkzTftz2zATdS4LMcUxKy0xvWl9ODzifo5SE/jgHAZN0+xZKCivdsmhAOAu\n" +
            "YhGgZnglZneuPNN6mwOrMgEYXWZmv24Uy7eju1FQIOiGoYUN/ch2/d6qbB1hxySlE2puG4GjfPpQ\n" +
            "/ZcRlfX4Bx9yyjwi0zKbcebPHVNowvzeL6h4d+/3EeKbbW0tNIniCA02eYUbKo7ir3Bq8lio+KBv\n" +
            "MFZHoWv6uEaWiOwEBsresuVpz3Eq3R8iuakVX49QGmE/z85L8JgByw0qh7xyZVZ7Aef9d8zcO89/\n" +
            "P5qehKbE09K1HBO9";

    private static final String PUBLIC_STR = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoQb+ty4E+RBr1sy5r8jv3/1uHzRsOlBLUtj277u5p2AvOM+WASeUtmUsJOYsIIqG1+zoFjMr6MfuYq98fPz5Zlb7t1xZeXHpJjKUE9qeuyeuzrFU+j0zwtTZhlacZCW75V0UjhZYrabC3e1j0lb59qWCn5NeSbLS1fqWq9PhIOOXh/FSV0E4idxGosxKcbh1iZuvK1hRRzuT2jZqV2jcVyye5vZ8Ij/5VDuOG0qitlTFzu7gPNAaHbuwi/+Rnm9dVdL6ZDrfyVjHUtoDrdNDECDQZuOVF9uVDqh32+lGcOYg/XAWSDLwbhoPto9arZO3dxRnEi8J0tQLt1DIk11l8QIDAQAB";

    public static void main(String[] args) throws Exception {


//        FileInputStream f = new FileInputStream("D:\\yuwell\\工商银行接口\\鱼跃易企付测试证书\\1104EH0012001.key");
//        byte[] bs = new byte[f.available()];
//        f.read(bs);
//        f.close();
//
//        bs = ReturnValue.base64enc(bs);


//        String pfxPath = "D:\\yuwell\\工商银行接口\\鱼跃易企付测试证书\\1104EH0012001.pfx";
//        String password = "95588";
//        //私钥：pfx文件中获取私钥对象
//        PrivateKey privateKey = PFXUtil.getPrivateKeyByPfx(pfxPath, password);
//        byte[] privateKeyByte = privateKey.getEncoded();
//        String privateKeyStr = new String(Base64.encodeBase64(privateKeyByte));
//        System.out.println("私钥Base64字符串：" + privateKeyStr);
//        //=====私钥Base64字符串转私钥对象
//        PrivateKey privateKey2 = PFXUtil.getPrivateKey(privateKeyStr);
//        System.out.println("私钥Base64字符串2：" + new String(Base64.encodeBase64(privateKey2.getEncoded())));
//        //证书：从pfx文件中获取证书对象
//        X509Certificate certificate = PFXUtil.getX509Certificate(pfxPath, password);
//
////        System.out.println("证书:"+certificate.getPublicKey().getAlgorithm());
////        System.out.println("==============="+certificate.getSigAlgName());
////        System.out.println("证书主题：" + certificate.getSubjectDN().getName());
//        String publicKeyStr = new String(Base64.encodeBase64(certificate.getPublicKey().getEncoded()));
//        System.out.println("公钥Base64字符串：" + publicKeyStr);
//        //=====根据公钥Base64字符串获取公钥对象
//        System.out.println("公钥Base64字符串2：" + new String(Base64.encodeBase64(PFXUtil.getPublicKey(publicKeyStr).getEncoded())));
//
//
//        String appId = "1104EH0012001";
//
//        String apiPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj03ctKM7KV+16\n" +
//                "bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCL\n" +
//                "T7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP\n" +
//                "3CiNgg0W6O3AGqwIDAQAB";

//        String myPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtxRSjLoQsUFXqmQ8L/TyV4CvYr9GVkLEdceteGc3wwscqliy3+la7KrmiM4LDsCPOGbRy7fa1wgTpSdXOe+brx3lB83GTrkQmU61SsSq/xnTQ8Kmq9TAXHkxGjB022EuZdgAUkQtot18j5oK0cSM9X/Sp2nc6h9Q0Nm6ufCYiY0AMvB7rWu0LUeLzKHp6/Cnr+rIZT1qpf23w+6x9dc1ZErUkQqWLVMwK2vQQr/rc38iOr+eJujaa81Tw9zE/bem0YsCKF4Bet+4HiDW2d3oI9PtXF9N71rBDZJP9Q4x+YbVhVkXmSCgJdw+ee4Jv6s3jJZBVlYCdkKd51FZ8UszBAgMBAAECggEBAKyZD4mR33jAhsYRJ0/qRW48jnCZGhxr7nBnwbfi+5oDbZ4QMfS/c7xOkLnaXi0O3FGPWJu7Xwxj3Ur2qqaAjPpuVDO8oXhlZI2JGaQEE4kOjuMl1/DVvuGHl42PCEl8h4KzIRtMgj+Nu7NF91dQ9Qa2M6cuNrTtS0pdMoyfBaWqssq/L9PoI2x+aJmtFYnSe+EgyO1DvndOsebcniltZrBSq4vN3vNg5Mu6k1U0mrmRaHs/+YRtDD4ppBXKS9KEujbrqL0NQFKyQ6WAA4vbfy03DuiF2fX7/eulqaUmHBPW9ALRRxWHPn+Fpeqdt+48gQDhdSh9Bs3vQ/tizxgM+E0CgYEA/y1AVQhMtv4MZ/hng5+/0xTP2QsMoGtflPgzt/6GgEe3W//a1h4p2GrTEh3/MoVzyiZ+m/N/oI5cMhIHNPW55oPOu6BXcvpmOs8mz1SgRoZN+tbNzfuTmeZpYZpQe4z/yzziBOI0yk+DoHZEx/5nE74yftw4wmew61hL1wJ9k/sCgYEArlSYjd2QyajqxaBCY5imnTbI5kT5Twlk2aFb1ji8z/olAKd/N/8ObE5Mt3a5SxJ0DT0VlBKyb9eP5ymmTsDy4cJXPo9YU1TaeHxv+nMJyJzKzApWEcWQhNiqrmTCbe6fIL29BXewMt5bACCvH0tKcjquCoZyGLPMlzxS25eoiXMCgYA0D8xvTNkyAJURZVnhPeeKLlXVp251EFMY9qa+pFxWscsUJxhD1TWeZPUZXith6F5eYQo10TE51bGzuX/k6zl5cWztVOJin/nZh8gYRuDIYsBoLAkjPG+514uqkSxYeMT7dzl525j/qcdmJpsT3rCzE3wD6/RcnYE4EvSuMmDinwKBgBhXiaGtgW0XIAkOUpbTltg28SBuGx21IxbcWBV2gK7Nr+MCgFoSTDJFUKMBeegIoJnEoMG3RqElSVuuziELAh8R4trCa0Pfgk32mjAvZftUIWESRGhg3Mgf9N07BocRHbkVpVIhH0JLGAvRhIJrIe8HUIv5r+7RFE09yBgPeiIPAoGBAOBiINN57DChoisPsw4qO4rsCuxHY0DMF+U50G4AYJgh56oIApVZcumhinBe1BW6tdGsBb2gJewztUwDGKi8pvM7AwIww4alTC0zCJ/IHCCOxtJiJRb3sRFVBufo4kpwEORW73bJWzqonEDF+qNhgDLBPwHYpb247OvEwOgbQP7m";
//
//
//        myPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDGWMsErdfKFsVWU04EDFyn89146wlwgF1M455tkUWsSdBU+fW+qoY1Cw7To59SsLVKOjaD3x4UYWOq2sxbHPtHdJIcr+T2go17Yo+9cWs9xrVBQf/YGrwxA+7fpUX9Rdnd3AJdiKhzYeqHCHpjLRglrhkFiskXhBxTz/eaPzDcwRMrunRO3PyuHwSuRdy/Zplj/AWzh1Yu/f48Curvg4pyPA7XsL1ulKVuO0wBO87G/9rZvuTgy+sSwFKrpGPeH4CNDzMjzjFF6PI8TNyZjH27kp+xiD6D/xtiVuMxkIgGO/Tz6JXFZpWQiTzuLnOMwm/q6/CvwpbYksWDAhwtMJG7AgMBAAECggEBAKquhiXEo0kYR0B990isip9Mz2zadQkGrUMZLKAEvvv/mPNHFh25CDxlA6FD0DqMLbVuWrMoMO5A0E+acPiG/AUFXyaohlNLDQz8R80x6KMEd71sWglRGFjllY2G36+PiGfOh4qNqsd/nx4MQaVOjNT/+DA4ucsuuGG3OM7XSejsB9b9HwsOswgVxlDqKkZDZbA2g1MNhTm12tgSp6ob43/s3RoEtqWHX9Ny/aS/GZO3EDXUsks20qZ7a+ZwDVS+dd2o44QyiGSyAy/7E/qZOeLr1QJt7x20K9gRjiXpLi9gu6ZVj6IzYCpF3onmvWu8yU4cCWjpM6fNhCsPyXB9PkECgYEA893C3O9QUktXxv71FktV6d7koGk0QnL34OYx/sfKPHnHRGt4SsYROjhiu0c1muA3TMWHqyTSbBdTDxlVTZB2JVbqecozJLvdcbcu68Og5G3IvXQkygbvU1Svk/SlZTFGrJ3NjOrB3UJnw9BUokXVRgbHE1mxvH4SawWugtL7UbMCgYEA0Dc61jcDsgkdId+vNxAn8gOFFgtxIE2Fy1inqbIoQbJwoMZhqXcLLMXNcra8AFu2sEPf42WKa1R6opSPh0rwW5L4JHknXbS4y8XkWI+OJBReDDo3PCXkQFgu2AT6lmaRCln8W6JlhZDwwNtxmPpmxs/NPVRiLU9LAeW7OHaz69kCgYAFeUHJcbmszzIeLlTT9kbsY+tfyly47RmxO5l9rYBVkSou1/awPvfeBav5piSG4Gd9WqWr6ryqXm+n1Bq9DfQI7IWAzFAPkAPtbH4qHo768Zfi4Lt+qV8v0KPMP16DAfMjJruZYR+O1tG7bG6p4cVit/456fZ6U54YNt1u5EttzwKBgDvk50Pfp2x7OqoX0isgtpzjblEdEXmdUzNfWTtzSCk9zzAwYL+CwvAqJiMtdX8U4sRpgenDt8GQF4bU/USJxgJRkJfNUgmehtn3HuxqvxSZ10PlKyZeL/5iX3c8V2kUL/yuTe1+FX4KZ3bSqSa8QPxHol5V+7fU61qsXuKrIq5hAoGAKfyiXQ/it6sC717Ywz23csza3TairoOwsOLRlS2vipUsuVdXa0e7jRmE9A8s3qq2BgxuMACYf/SidJ6Cmrju6CLsbIhvCv7KlJsa3rmNthvE58wiy9aXKiOVqsDZimRxxiDcK/2oWsJNTQLg8x3lCLZjkn2/RTQi6IEeLyIEIBY=";
//
//        String caPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxljLBK3XyhbFVlNOBAxcp/PdeOsJcIBdTOOebZFFrEnQVPn1vqqGNQsO06OfUrC1Sjo2g98eFGFjqtrMWxz7R3SSHK/k9oKNe2KPvXFrPca1QUH/2Bq8MQPu36VF/UXZ3dwCXYioc2Hqhwh6Yy0YJa4ZBYrJF4QcU8/3mj8w3METK7p0Ttz8rh8ErkXcv2aZY/wFs4dWLv3+PArq74OKcjwO17C9bpSlbjtMATvOxv/a2b7k4MvrEsBSq6Rj3h+AjQ8zI84xRejyPEzcmYx9u5KfsYg+g/8bYlbjMZCIBjv08+iVxWaVkIk87i5zjMJv6uvwr8KW2JLFgwIcLTCRuwIDAQAB";

//        DefaultIcbcClient client = new DefaultIcbcClient(appId, myPrivateKey, caPublicKey);

        DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY, MY_PUB_KEY, PASSWORD);
////
////
//////        EbankcSignInfoQueryRequestV1 request = new EbankcSignInfoQueryRequestV1();
//////        request.setServiceUrl("https://gw.open.icbc.com.cn/ui/b2c/pay/transfer/V1");
////


        // 设置请求对象request
        EnterpriseOpenpayDirectPayRequestV1 request = new EnterpriseOpenpayDirectPayRequestV1();
//
        // 设置请求路径
        request.setServiceUrl("https://apipcs3.dccnet.com.cn/api/enterprise/openpay/directpay/V1");

////
        EnterpriseOpenpayDirectPayRequestV1.EnterpriseOpenpayDirectPayV1Biz bizContent = new EnterpriseOpenpayDirectPayRequestV1.EnterpriseOpenpayDirectPayV1Biz();
////
        //平台数据证书
        bizContent.setFile_id("1104EH0012001.e.1104");
        //0-同步，1-异步
        bizContent.setPay_mode("0");
        //0-免签 1-非免签
        bizContent.setSign_flag("0");
        //针对同步非免签和同步免签超限后返回重定向URL的分支判断；0-PC 1-WAP，目前只支持PC，上送WAP会导致重定向失败
        bizContent.setChannel_flag("0");
        //在银行端注册一级平台时生成的号码
        bizContent.setFile_code("1104EH0012001");
        //在银行端注册一级平台时生成的名称
        bizContent.setFile_name("鱼跃医疗");
        //平台生成的唯一序列号
        bizContent.setFile_serialno("11049000003933");
        //交易平台公司全称
        bizContent.setTradeplat_name("鱼跃医疗器械股份有限公司");
        //交易平台在银行端注册时生成的号码（false）
        bizContent.setTradeplat_code("20181206000000003");
        //trade_orgcode交易平台统一社会信用代码(false)
        bizContent.setTrade_orgcode("91350922MA34A6CP84");
        //acct_orgcode终端客户统一社会信用代码
        bizContent.setAcct_orgcode("12345");
        //平台发起支付时传递的单号
        bizContent.setOrder_no("order123456789");
        //trade_time 交易平台发起的时间，时间需在系统前1小时至12小时之间，否则为错误订单，不允许提交；格式：YYYYMMDDHHMMSS
        bizContent.setTrade_time("20181221170000");
        //orderAmt 300，单位：分
        bizContent.setOrderAmt("3000");
        //amout 100，单位：分
        bizContent.setAmout("3000");
        //currtype 001，人民币，目前支持人民币
        bizContent.setCurrtype("001");
        //pay_name 企业在银行开办的对公账户户名
        bizContent.setPay_name("鱼跃对公账户");
        //pay_acct_num 企业在银行开办的对公账户，支持综合账户19-9
        bizContent.setPay_acct_num("1104021009000097469");
        //pay_phoneno 付方联系方式	010-8270XXXX(False)
        bizContent.setPay_phoneno("010-82706722");
        //get_province 收货方省份	北京市(False)
        bizContent.setGet_province("北京市");
        //get_county 收货人区县	海淀区（False）
        bizContent.setGet_county("海淀区");
        //get_city 收货人城市	北京(False)
        bizContent.setGet_city("北京");
        //get_email 收货人邮箱	xxx@139.com
        bizContent.setGet_email("www@139.com");
        //get_phone 收货人电话	132xxxxxxxx(False)
        bizContent.setGet_phone("13426351013");
        //get_address 收货人联系方式	北京市海淀区中关村软件园XX号XXX楼(False)
        bizContent.setGet_address("北京市海淀区中关村软件园XX号XXX楼");
        //get_post 收货人邮编	100101(False)
        bizContent.setGet_post("100101");
        //tradeplat_rem 交易平台备注	交易平台备注(False)
        bizContent.setTradeplat_rem("交易平台备注");
        //pay_rem 付款备注	付款备注(False)
        bizContent.setPay_rem("付款备注");
        //order_rem 订单备注	订单备注(False)
        bizContent.setOrder_rem("订单备注");
        //rec_name 收款名称	企业在银行开办的对公账户户名；支持他行介质
        bizContent.setRec_name("收款名称");
        //Rec_acct_num收款账号	企业在银行开办的对公账户，支持综合账户19-9；支持他行介质
        bizContent.setRec_acct_num("3602009009000401956");
        //rec_bnkclscode 收款行联行号	收款行联行号，人行规定的联行行号；他行收款时必输，否则会影响付款（False）
        bizContent.setRec_bnkclscode("");
        //rec_orgcode 收款统一社会信用代码	收款统一社会信用代码（False）
        bizContent.setRec_orgcode("sktyshxydm");
        //system_flag 标志收款人是否属于工行用户 1、系统内 2、系统外
        bizContent.setSystem_flag("1");
        //language 语言标志	字典：ZH_CN；EN_US
        bizContent.setLanguage("ZH_CN");











        List<EnterpriseOpenpayDirectPayRequestV1.EnterpriseOpenpayDirectPayGoodInfo> goodlist = new ArrayList<EnterpriseOpenpayDirectPayRequestV1.EnterpriseOpenpayDirectPayGoodInfo>();
////
        EnterpriseOpenpayDirectPayRequestV1.EnterpriseOpenpayDirectPayGoodInfo goodinfo1 = new EnterpriseOpenpayDirectPayRequestV1.EnterpriseOpenpayDirectPayGoodInfo();
        //goods_seqno	str	true	5	商品信息子序号	标识商品序列号
        goodinfo1.setGoods_seqno("SP001");
        //goods_name	str	true	500	商品名称	钢材
        goodinfo1.setGoods_name("苹果");
        //goods_num	str	true	10	商品总数	100
        goodinfo1.setGoods_num("10");
        //price	str	false	17	含税单价	1000000，单位：分，人民币，1000000 = 1万人民币
        goodinfo1.setPrice("300");
        //seller_name	str	true	60	销售方联系人名称	张三
        goodinfo1.setSeller_name("appler_seller");
        //seller_phoneno	str	true	30	销售方联系方式	138xxxxxxxx
        goodinfo1.setSeller_phoneno("13811111111");
        //units	str	false	30	计量单位
        goodinfo1.setUnits("KG");
        //weight	str	false	17	订单商品规格	吨
        goodinfo1.setWeight("3");
//        EnterpriseOpenpayDirectPayRequestV1.EnterpriseOpenpayDirectPayGoodInfo goodinfo2 = new EnterpriseOpenpayDirectPayRequestV1.EnterpriseOpenpayDirectPayGoodInfo();
//        goodinfo2.setGoods_name("香蕉");
//        goodinfo2.setGoods_num("11");
//        goodinfo2.setGoods_seqno("SP002");
//        goodinfo2.setPrice("850");
//        goodinfo2.setSeller_phoneno("13811111111");
//        goodinfo2.setSeller_name("appler_seller");
//        goodinfo2.setUnits("KG");
//        goodinfo2.setWeight("3");

        goodlist.add(goodinfo1);
//        goodlist.add(goodinfo2);
        bizContent.setGoodlist(goodlist);

        request.setBizContent(bizContent);

        EnterpriseOpenpayDirectPayResponseV1 response = null;

        Random rand = new Random();
        String msgId = rand.nextInt(99999) + "msg";

        try {
            response = client.<EnterpriseOpenpayDirectPayResponseV1>execute(request, msgId);
            if (response.isSuccess()) {
                // 业务成功处理
                System.out.println("success");//
            } else {
                // 失败
                System.out.println("error");
            }
        } catch (IcbcApiException e) {
            e.printStackTrace();
        }

//        EbankcSignInfoQueryRequestV1.EbankcSignInfoQueryRequestV1Biz bizContent = new EbankcSignInfoQueryRequestV1.EbankcSignInfoQueryRequestV1Biz();
//        bizContent.setNextTag("");
//        bizContent.setTranDateBegin("10160910");
//        bizContent.setTranDateEnd("30170920");
//        bizContent.setVerifiedCode("0200EG0000602");
//        bizContent.setSerialNo("123");
//
//        request.setBizContent(bizContent);
//
//        EbankcSignInfoQueryResponseV1 response = client.execute(request, UUID.randomUUID().toString());
//
//        if (response.isSuccess()) {
//            // 业务成功处理
//            if(response.getQrylist().size() > 0){
//                System.out.println(response.getQrylist().get(0).getSerialNo());
//            }
//        } else {
//            // 失败
//            System.out.println(response.getReturnMsg());
//        }

    }

}
