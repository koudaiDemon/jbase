package com.cwww.base;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/28  11:19
 */
public class Demo2 {

    public static void main(String[] args) {

        Double str = Double.valueOf("41395.92");

        BigDecimal bigDecimal = BigDecimal.ZERO;
        bigDecimal.add(BigDecimal.valueOf(20.0));

        double price = 0.0;

        price += 20;
        System.out.println(price);

        System.out.println(bigDecimal);

        System.out.println();

        System.out.println(String.format("%.3f",str));

        String str1 = "\\\"12123213123123\\\"";

        System.out.println(str1);
        System.out.println(StringEscapeUtils.unescapeJava(str1));

        String html = "<li>we'dadasdsad</li>";

        System.out.println(StringEscapeUtils.escapeHtml(html));

        String format = "asdasdas%s,%s";

        System.out.println(String.format(format,"==","##"));

        String s = StringUtils.stripStart("000011111111", "0");
        System.out.println(s);

        boolean sameDay = DateUtils.isSameDay(new Date(), new Date());

        System.out.println(sameDay);

    }

}
