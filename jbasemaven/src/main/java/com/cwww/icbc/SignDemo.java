package com.cwww.icbc;

import cn.com.infosec.icbc.ReturnValue;
import com.icbc.api.IcbcConstants;
import com.icbc.api.crypt.IcbcCa;
import com.icbc.api.utils.IcbcSignature;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/11/12  10:38
 */
public class SignDemo {

    protected static final String MY_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCKKbTYJwXVVIKDwf0ILucC/OaXEvLalc0wdVH+KUZwFahdt+m2zqQtB0VKDbGpFISpexpdXFl1RgwsLUxjdKnHL6EJJl6P4Ub8GDmIskaJn4lfDksHdOgpeXV7ceaBWrNkOZgtqRJYNPrU49qqIbw6SEf9oGYD/OyDXv01g6JnN1VtYDsruA8j1xZG9MTYUircmnb9Pagva73HsR2Z3zSCRBxNLzi7K2n8U8Tt1HggM1sSg9OGLTFIoC0uiXlOKZggVYBEym4RH6kSfydRAdtbe12eHTNp6VQ2LgErga3+TzG2lDjO2Nh3jq4hiM5ArLZ5TY9pEmXaPB55sgQxHo2/AgMBAAECggEAKlnYdn2XuynZi/vVzUFbTL4rDNohGUHcEOOVi5wpXEg3QOsUOBYlmyvkYGvMLrD8bVsv3LhnL5yWNKB6Du5+8HC4Iy7gw6fhc3sVf8Jt/tepazrdYt7CH5x8l6d1aedcn/dLn4t49RLgZIunxojqws7PSn51ZvmLokVmxpWgHZURr6UYX2XooH1nhOHidqMWJO7946dgvGWZTm2Ee6xEErQIWRyLiSdONh9yYYPwiZdXJZALWz6QxhZBgCHR6gxcg4eG5YBE1DEz8pIL0YInxLxa/IX+Kxfj/lytcRX1W+dIzLKAmWWu/82NE72OF7+8Z14U6Xibz5IJm1byz+8O6QKBgQDonmtEEFa8kUxhKgQAagBfBn9Ri6O9k9zFXv122iFkmlx2gYiTTCBX8oXy7iuiKvgDGbQXZf5TKt9S9DorxgMexSw45i331P9PzX0uVQstMLsbpJK8zEFI5guB4sS+Y9NQ9zxPkWEdCah2P/69bgW1N3TE783ve9s8WlB5mkLxFQKBgQCYDNDMsOTE8DroqzOTc6opOAybwcqXQbOFmrVsfzmxYtaLpPn3Y8R3zP9SUviYae+6cMmBIiJAZZzEyeJl6VO+adQIkf1yZKzUOEBysDfBvfkB05q3CDcPoTl50f2tz8SlWecnmCU22sihQGey/XjVghejJA8kDrWV8DlIitRwgwKBgQDcC/f2Y+pY6K2zAFbWuW9udKv9zLVxAS2He2sTghaynx62b6RtikbSSzYQ7uJRvonsDOWCeNYTgGvPgf6BKAgNlIFXtc5oMZHuwLkZGhO/hOiKpqy0cheTySG7lXN9ktr0LVxc/G48VMWKTtFACpLVsQcBwMMzNxvVxK15ysZmXQKBgQCGP4p7sBPM7m6Uz9QR9KXhDuPTAh/lvNTMufRFf29o92OGmHs7upLVU+J6xVdI+v/3FW5U3dZwFUd9WOLUKFcQYFAd4Qht4AXYunHs0Tnpjdo1iqenw1EaM7eTn5zjwfZWKDK3+tWgwTszZLIqjzIJEXvdRxE3POWHJTrumeVIEQKBgQC+KpxziJ85S+Q4vWCWzj0RWyMxYENeR/SeEmBy3AKUJKGQnswpegmTCw7FY+bji5ayGFYtYwL1RZbKXivTHsjsPIoK/S+8XzONwwSw6YT1RD64Ewx9PQQWOCcR9ZFys787HTwGbsNgIELspuT3h77e6+X/BNFUtgrJ6tvGx9CmwA==";

    private static final String PUBLIC_STR = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoQb+ty4E+RBr1sy5r8jv3/1uHzRsOlBLUtj277u5p2AvOM+WASeUtmUsJOYsIIqG1+zoFjMr6MfuYq98fPz5Zlb7t1xZeXHpJjKUE9qeuyeuzrFU+j0zwtTZhlacZCW75V0UjhZYrabC3e1j0lb59qWCn5NeSbLS1fqWq9PhIOOXh/FSV0E4idxGosxKcbh1iZuvK1hRRzuT2jZqV2jcVyye5vZ8Ij/5VDuOG0qitlTFzu7gPNAaHbuwi/+Rnm9dVdL6ZDrfyVjHUtoDrdNDECDQZuOVF9uVDqh32+lGcOYg/XAWSDLwbhoPto9arZO3dxRnEi8J0tQLt1DIk11l8QIDAQAB";

    protected static final String APIGW_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB";


    public static void main(String[] args) throws Exception {

//        String myPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtxRSjLoQsUFXqmQ8L/TyV4CvYr9GVkLEdceteGc3wwscqliy3+la7KrmiM4LDsCPOGbRy7fa1wgTpSdXOe+brx3lB83GTrkQmU61SsSq/xnTQ8Kmq9TAXHkxGjB022EuZdgAUkQtot18j5oK0cSM9X/Sp2nc6h9Q0Nm6ufCYiY0AMvB7rWu0LUeLzKHp6/Cnr+rIZT1qpf23w+6x9dc1ZErUkQqWLVMwK2vQQr/rc38iOr+eJujaa81Tw9zE/bem0YsCKF4Bet+4HiDW2d3oI9PtXF9N71rBDZJP9Q4x+YbVhVkXmSCgJdw+ee4Jv6s3jJZBVlYCdkKd51FZ8UszBAgMBAAECggEBAKyZD4mR33jAhsYRJ0/qRW48jnCZGhxr7nBnwbfi+5oDbZ4QMfS/c7xOkLnaXi0O3FGPWJu7Xwxj3Ur2qqaAjPpuVDO8oXhlZI2JGaQEE4kOjuMl1/DVvuGHl42PCEl8h4KzIRtMgj+Nu7NF91dQ9Qa2M6cuNrTtS0pdMoyfBaWqssq/L9PoI2x+aJmtFYnSe+EgyO1DvndOsebcniltZrBSq4vN3vNg5Mu6k1U0mrmRaHs/+YRtDD4ppBXKS9KEujbrqL0NQFKyQ6WAA4vbfy03DuiF2fX7/eulqaUmHBPW9ALRRxWHPn+Fpeqdt+48gQDhdSh9Bs3vQ/tizxgM+E0CgYEA/y1AVQhMtv4MZ/hng5+/0xTP2QsMoGtflPgzt/6GgEe3W//a1h4p2GrTEh3/MoVzyiZ+m/N/oI5cMhIHNPW55oPOu6BXcvpmOs8mz1SgRoZN+tbNzfuTmeZpYZpQe4z/yzziBOI0yk+DoHZEx/5nE74yftw4wmew61hL1wJ9k/sCgYEArlSYjd2QyajqxaBCY5imnTbI5kT5Twlk2aFb1ji8z/olAKd/N/8ObE5Mt3a5SxJ0DT0VlBKyb9eP5ymmTsDy4cJXPo9YU1TaeHxv+nMJyJzKzApWEcWQhNiqrmTCbe6fIL29BXewMt5bACCvH0tKcjquCoZyGLPMlzxS25eoiXMCgYA0D8xvTNkyAJURZVnhPeeKLlXVp251EFMY9qa+pFxWscsUJxhD1TWeZPUZXith6F5eYQo10TE51bGzuX/k6zl5cWztVOJin/nZh8gYRuDIYsBoLAkjPG+514uqkSxYeMT7dzl525j/qcdmJpsT3rCzE3wD6/RcnYE4EvSuMmDinwKBgBhXiaGtgW0XIAkOUpbTltg28SBuGx21IxbcWBV2gK7Nr+MCgFoSTDJFUKMBeegIoJnEoMG3RqElSVuuziELAh8R4trCa0Pfgk32mjAvZftUIWESRGhg3Mgf9N07BocRHbkVpVIhH0JLGAvRhIJrIe8HUIv5r+7RFE09yBgPeiIPAoGBAOBiINN57DChoisPsw4qO4rsCuxHY0DMF+U50G4AYJgh56oIApVZcumhinBe1BW6tdGsBb2gJewztUwDGKi8pvM7AwIww4alTC0zCJ/IHCCOxtJiJRb3sRFVBufo4kpwEORW73bJWzqonEDF+qNhgDLBPwHYpb247OvEwOgbQP7m";
//        String privateKey = "";
//
//        FileInputStream f = new FileInputStream("D:\\yuwell\\工商银行接口\\鱼跃易企付测试证书\\1104EH0012001.key");
//        byte[] bs = new byte[f.available()];
//        f.read(bs);
//        f.close();
////
//        bs = ReturnValue.base64enc(bs);
////
//        System.out.println(new String(bs));
//
//
//        System.out.println("==================================");
//
        FileInputStream f1 = new FileInputStream("D:\\yuwell\\工商银行接口\\鱼跃易企付测试证书\\1104EH0012001.crt");
        byte[] bs1 = new byte[f1.available()];
        f1.read(bs1);
        f1.close();
        bs1 = ReturnValue.base64enc(bs1);
        System.out.println(new String(bs1));
////
        System.out.println("===========================");
////
//        String str = "aaaaaaa";
////
////        byte[] sign = ReturnValue.sign(str.getBytes(), str.getBytes().length, ReturnValue.base64dec(bs), "95588".toCharArray());
////        byte[] EncSign = ReturnValue.base64enc(sign);
////        String merSignMsgBase64 = new String(EncSign);
////        System.out.println(merSignMsgBase64);
//
//
////        IcbcCa.sign(str.getBytes(), new String(bs,"GBk"), "95588");
////        String pfxPath = "D:\\yuwell\\工商银行接口\\鱼跃易企付测试证书\\1104EH0012001.pfx";
////        String password = "95588";
//        //私钥：pfx文件中获取私钥对象
////        PrivateKey privateKey = PFXUtil.getPrivateKeyByPfx(pfxPath, password);
////        byte[] privateKeyByte = privateKey.getEncoded();
////        String privateKeyStr = new String(Base64.encodeBase64(privateKeyByte));
////        System.out.println("私钥Base64字符串：" + privateKeyStr);
//
//        byte[] sign = ReturnValue.sign(str.getBytes(), str.getBytes().length, ReturnValue.base64dec(bs),
//                "95588".toCharArray());
////
//        System.out.println(sign.length);
//
//        System.out.println(new String(ReturnValue.base64enc(sign)));
//
//        String respBizContentStr = "{\"response_biz_content\":{\"return_code\":400011,\"return_msg\":\"app id is not registered\",\"msg_id\":\"87933msg\"},\"sign\":\"I21ynAn9REv2z+8ssELiTXb7REatdHlXXj+U4WcRZnOQpSL6FRTYfYNJVyRCb62l1iRS2TkjjpEFhMhdDMWeFHc/zSQDD0P36wlRk87u7exDMigrJJyVFe6+TJX0LnHhobtMwxPRZfUjhHxnubRH7yUtLjMpwwCVY3G3AzPS5UI=\"}";

//        String sign = "I21ynAn9REv2z+8ssELiTXb7REatdHlXXj+U4WcRZnOQpSL6FRTYfYNJVyRCb62l1iRS2TkjjpEFhMhdDMWeFHc/zSQDD0P36wlRk87u7exDMigrJJyVFe6+TJX0LnHhobtMwxPRZfUjhHxnubRH7yUtLjMpwwCVY3G3AzPS5UI=";

//        boolean b = IcbcSignature.verify(respBizContentStr,
//                IcbcConstants.SIGN_TYPE_CA, APIGW_PUBLIC_KEY, "utf-8", sign);
//        System.out.println(b);

//        byte[] src = str.getBytes();
//
//        byte[] result = (byte[])null;
//
//        try {
////            PKCS8EncodedKeySpec prikeySpec = new PKCS8EncodedKeySpec(privateKey);
////            KeyFactory kf = KeyFactory.getInstance("RSA", "INFOSEC");
////            PrivateKey pkey = kf.generatePrivate(prikeySpec);
//            Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "INFOSEC");
//            rsaCipher.init(1, privateKey);
//            int blockSize = rsaCipher.getBlockSize();
//            int inLen = src.length;
//            result = new byte[(inLen + blockSize - 1) / blockSize * (blockSize + 11)];
//            byte[] temp = (byte[])null;
//            int outOffset = 0;
//
//            int inOffset;
//            for(inOffset = 0; inLen > blockSize; outOffset += temp.length) {
//                rsaCipher.init(1, privateKey);
//                temp = rsaCipher.doFinal(src, inOffset, blockSize);
//                System.arraycopy(temp, 0, result, outOffset, temp.length);
//                inOffset += blockSize;
//                inLen -= blockSize;
//            }
//
//            rsaCipher.init(1, privateKey);
//            temp = rsaCipher.doFinal(src, inOffset, inLen);
//            System.arraycopy(temp, 0, result, outOffset, temp.length);
//        } catch (Exception var15) {
//            var15.printStackTrace();
//            result = (byte[])null;
//        } finally {
//            ;
//        }


    }

}
