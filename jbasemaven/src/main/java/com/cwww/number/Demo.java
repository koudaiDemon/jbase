package com.cwww.number;

import java.math.BigDecimal;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/9  10:27
 */
public class Demo {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("0.100000000000000000000123");
        BigDecimal bigDecimal1 = new BigDecimal("0.1");
        System.out.println(bigDecimal.compareTo(bigDecimal1));

        String str = "https://sandbox-api.openpay.mx/v1/m8cxhbiqh0t9alitrcik/charges/trcy2dwf3sgzaydxigbb/redirect/";
        System.out.println(str.endsWith("redirect/"));

    }

}
