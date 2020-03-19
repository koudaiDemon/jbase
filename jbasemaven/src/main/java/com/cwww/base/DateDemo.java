package com.cwww.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author cWww
 * @Title DateDemo
 * @Description 用于测试demo
 * @date: 2019/3/19  9:27
 */
public class DateDemo {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static String format(Date date){
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static Date parse(String date) throws ParseException {
        return SIMPLE_DATE_FORMAT.parse(date);
    }

    public static String newFormat(LocalDateTime date){
        return DATE_TIME_FORMATTER.format(date);
    }

    public static LocalDateTime newParse(String date){
        return LocalDateTime.parse(date,DATE_TIME_FORMATTER);
    }

    /**
     * SimpleDateFormat多线程样例
     * @throws InterruptedException
     */
    public static void test1() throws InterruptedException{
        ExecutorService service = Executors.newFixedThreadPool(100);

        for (int i = 0 ; i < 20 ; i++) {
            service.submit(()->{
                for (int j = 0 ; j < 10 ; j++) {
                    try {
                        System.out.println("thread:"+Thread.currentThread().getName()+format(parse("2019-03-19 11:12:13")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
//        service.shutdown();
//        service.awaitTermination(1,TimeUnit.DAYS);
    }

    /**
     * DateTimeFormatter多线程样例
     * @throws InterruptedException
     */
    public static void test2() throws InterruptedException{
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0 ; i < 20 ; i++) {
            service.submit(()->{
                for (int j = 0 ; j < 10 ; j++) {
                    System.out.println("thread:"+Thread.currentThread().getName()+newFormat(newParse("2019-03-19 11:12:13")));
                }
            });
        }

        service.shutdown();
        service.awaitTermination(1,TimeUnit.DAYS);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Instant.now().atOffset(ZoneOffset.ofHours(8)));
        System.out.println(LocalDateTime.ofInstant(Instant.now(),ZoneId.systemDefault()).toLocalDate());
        System.out.println(LocalDateTime.ofInstant(Instant.now(),ZoneId.systemDefault()).toLocalTime());
//        test2();
    }


}
