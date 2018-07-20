package com.cwww.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/13  17:39
 */
public class TestDate {

    public static void main(String[] args) throws Exception {

        Date date = new Date();

        String str = date.toString();
        System.out.println(str);
        Calendar calendar = Calendar.getInstance();
//        DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", new Locale(System.getProperty("user.language")));
        DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        System.out.println(System.getProperty("user.language"));
//        Date parsedNow = null;

//        calendar.setTime(date.toString());
        System.out.println(format.parse(str));
    }

}
