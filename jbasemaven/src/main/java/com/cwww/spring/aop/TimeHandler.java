package com.cwww.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/4/12  8:55
 */
public class TimeHandler {

    public void printTime(ProceedingJoinPoint joinPoint,String msg) throws Throwable {
        System.out.println("currentTime:"+System.currentTimeMillis());
        final Object proceed = joinPoint.proceed(joinPoint.getArgs());
        System.out.println(proceed);
    }

}
