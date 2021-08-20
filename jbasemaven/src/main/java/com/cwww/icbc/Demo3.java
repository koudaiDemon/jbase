package com.cwww.icbc;

import cn.com.infosec.icbc.ReturnValue;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcApiException;
import com.icbc.api.request.EbankcSignInfoQueryRequestV1;
import com.icbc.api.request.EnterpriseOpenpayDirectPayRequestV1;
import com.icbc.api.response.EbankcSignInfoQueryResponseV1;
import com.icbc.api.response.EnterpriseOpenpayDirectPayResponseV1;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

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

    protected static final String MY_PRIVATE_KEY = "BKgCeUXRxYOrrGsu/J2KODF1GY0wwHBW4v47wjGr5+2QP1boAG7gKeovqnoMpQ+daWq+KJX4NS4f\n" +
            "1DUcAfuT/WxUbEIkOPifn5/PU3MknUJNZYppRl21D+ssXXARHF+klChWY8E8cigZEd/p+8yfHiC4\n" +
            "xaAah8znpxFjDPQvL017KQFLiw96szaA8RTx4rDJd0XLew6yefR8eYfTfXSnh9klFm9F4Hi7yj6K\n" +
            "KYB4+ooUtpkzWm14bSWYEP84gKJLoeeBMFCj+bn5X5EXQKIY/P4PKOMH8pfe7T5zj/iMzCacdrqb\n" +
            "RyjJ+i1dYMz+Cwn4UTz+IiVcE4JTP3W+84l91Cva1w+HzoF6wT8wFVInC4z6wgtB7XFiaVper+UW\n" +
            "IkRQlKRhHprwTZBy5kCqTBXnkam5S0ALLTTNu+YYpK9jpnBzb3RWwmV63v8RZjUfuuTO8JrpYnzN\n" +
            "/sJW3p/4HNc3xWJf+DO23W/dGp7LDa+EM2qq/R3orKWMUGBXxEOM8C6WEVZ2XIA42rIKSPq/anlh\n" +
            "P73RHdRRx+P1bytQCUalo5AHjlfs5JyRou52Z6PafttKl2cySe57BUZgNxMdnAnPKzQ4acGO1eQi\n" +
            "AHopMm9VT3ZGLfa2mP77EDg57A+7mpI93kCuL7Kh6Gnorb7apNYVIkvHSlFSrtFUxon7PMCp22jJ\n" +
            "xWboYVhMKTpTenGmyTiIF/3EGjVJWRNCtUtm6gb0tQcEZypXfi1IIzUdR525mlp//KYTNZc9zAdu\n" +
            "EIbwcu9AztZhpnGKlsaqazq9MCup4IZ5+VhVQdDKTddCVV0uiko90OBnYKYiXbLg5ERg0z1W5EPC\n" +
            "WUADCUoV6t367+ic5yWQURolyqL06B9OQ95YRtDbuweXsONVobA7J4JNPNel/HmuHgyJdnIUadHJ\n" +
            "CsoQLVsYyVhG/a/IXG1+G052gihlnP+ybwg68ul1C4g1exIkQ3nR6hVnPv1FztK4y5oVc08OPdYe\n" +
            "kYw+cjLoqoEouwbdjvDq74ldV7hz/vI/SzL7FzGHfZSMSR1f//lhQFanPVDC3k0CCVuTVJe+BiAc\n" +
            "bkoL24mG1Q+HgMkAYUOFxHQGkItGrDV4plxNJe6bxSYjg4/osTbiWC2CuuaovAemG/5J8SsbhUDV\n" +
            "DDq7X06bC5+g3l79zeRfJ/NVOfov3UpoHZKn5MhRlHJ53NyXu6Brn8nsvckAstUfzg3W+580sdoW\n" +
            "meGXfLhECl9e3mbOnZZheuGKyP2RrqPkTOOagWlCu5mEA+xBc+JH4E90lX496h62JRHZAvVcw3Yg\n" +
            "1icxuCbA5KrHwQDMFeiFgdVQNUzI5cZLLoO730R0326Y1ReHMZrGAENyXNBWjuYJ/CDsGJShA+iY\n" +
            "FTDwutNLTJCmTp50ZA6fiAaPdAOjH/ui237l67/lJnhNPu04YznnO+ABo4avPyAhmFEV052ndU3d\n" +
            "77ws9z/9X+gjL9Mv/sZcAxz9iJuwmqT0eLFZdidwnNjMVpQRbSmW3U/lSazgjnpTmH+ummHxdps9\n" +
            "m1NpDa1mUFK+038Y+qpf+IEZwZ1Q82dZTUUJr13HgLQn0EJhgTHX6TOtuXbPeNDKTZlE9128";
    protected static final String APIGW_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";

    protected static final String APP_ID = "1104EH0064001";

    protected static final String PASSWORD = "95588";

    protected static final String MY_PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjVD5SoHMZ0upYCUAID6/lcZqQYeStvhXRdoE83q0jkTbNrQ+TdaTeCpoRLurn1QJwGrK3C/vjPaMUrb9onlsY41BQMu19yOFPdeTbg2BnkF8feA5KBqT1/QNm1lMKIWqSCRWagOqFMAz41XuZgD2BHjIQjS+IG3SXz/iYue8HKfiVbUOsgkK7eGF4j2hesgGW48TEZdQOvZ8pmlNftA3/u4EH+kSJNPqIfwxgLQ9RXxJND3yD93o9M9K9WXM/yMsypxhfusol7R6OkdUMsv1nO+PevwWZtjrRKQ3RI55tp02M/4rgXDZSp3FYPJvqIADYbsRxCymEvAbSP3saAvAvwIDAQAB";

    private static final String PUBLIC_STR = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoQb+ty4E+RBr1sy5r8jv3/1uHzRsOlBLUtj277u5p2AvOM+WASeUtmUsJOYsIIqG1+zoFjMr6MfuYq98fPz5Zlb7t1xZeXHpJjKUE9qeuyeuzrFU+j0zwtTZhlacZCW75V0UjhZYrabC3e1j0lb59qWCn5NeSbLS1fqWq9PhIOOXh/FSV0E4idxGosxKcbh1iZuvK1hRRzuT2jZqV2jcVyye5vZ8Ij/5VDuOG0qitlTFzu7gPNAaHbuwi/+Rnm9dVdL6ZDrfyVjHUtoDrdNDECDQZuOVF9uVDqh32+lGcOYg/XAWSDLwbhoPto9arZO3dxRnEi8J0tQLt1DIk11l8QIDAQAB";

    public static void main(String[] args) throws Exception {



//
//        bs = ReturnValue.base64enc(bs);
//
//
//        String pfxPath = "D:\\yuwell\\工商银行接口\\鱼跃易企付测试证书\\1104EH0064001.pfx";
//        String password = "95588";
////        //私钥：pfx文件中获取私钥对象
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




//        System.out.println(MY_PRIVATE_KEY.replace("\n",""));
//        System.out.println("=======================");
//        System.out.println(APIGW_PUBLIC_KEY.replace("\n",""));
//        System.out.println("=======================");
//        System.out.println(MY_PUB_KEY.replace("\n",""));


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
        bizContent.setFile_id("1104EH0064001.e.1104");
        //0-同步，1-异步
        bizContent.setPay_mode("0");
        //0-免签 1-非免签
        bizContent.setSign_flag("0");
        //针对同步非免签和同步免签超限后返回重定向URL的分支判断；0-PC 1-WAP，目前只支持PC，上送WAP会导致重定向失败
        bizContent.setChannel_flag("0");
        //在银行端注册一级平台时生成的号码
        bizContent.setFile_code("1104EH0012001");
        //在银行端注册一级平台时生成的名称
        bizContent.setFile_name("劣剥咪属堂洽矩淌气入词仲沈僧");
        //平台生成的唯一序列号
        bizContent.setFile_serialno("123456789");
        //交易平台公司全称(false)
        bizContent.setTradeplat_name("江苏鱼跃医疗设备股份有限公司");
        //交易平台在银行端注册时生成的号码（false）
        bizContent.setTradeplat_code("11049000003933");
        //trade_orgcode交易平台统一社会信用代码(false)
        bizContent.setTrade_orgcode("91350922MA34A6CP84");
        //acct_orgcode终端客户统一社会信用代码(false)
        bizContent.setAcct_orgcode("12345");
        //平台发起支付时传递的单号
        bizContent.setOrder_no("order123456789");
        //trade_time 交易平台发起的时间，时间需在系统前1小时至12小时之间，否则为错误订单，不允许提交；格式：YYYYMMDDHHMMSS
        bizContent.setTrade_time("20181223093000");
        //orderAmt 300，单位：分
        bizContent.setOrderAmt("3000");
        //amout 100，单位：分
        bizContent.setAmout("3000");
        //currtype 001，人民币，目前支持人民币
        bizContent.setCurrtype("001");
        //pay_name 企业在银行开办的对公账户户名
        bizContent.setPay_name("江苏鱼跃医疗设备股份有限公司");
        //pay_acct_num 企业在银行开办的对公账户，支持综合账户19-9
        bizContent.setPay_acct_num("1104010309000295713");
        //pay_phoneno 付方联系方式	010-8270XXXX(False)
        bizContent.setPay_phoneno("010-82706722");
        //get_province 收货方省份	北京市(False)
        bizContent.setGet_province("北京市");
        //get_county 收货人区县	海淀区（False）
        bizContent.setGet_county("海淀区");
        //get_city 收货人城市	北京(False)
        bizContent.setGet_city("北京");
        //get_email 收货人邮箱	xxx@139.com(false)
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
//        bizContent.setRec_bnkclscode("");
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

//        Random rand = new Random();
        String msgId = UUID.randomUUID().toString();

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
