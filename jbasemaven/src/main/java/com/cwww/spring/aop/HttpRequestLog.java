package com.cwww.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author cWww
 * @Title HttpRequestLog
 * @Description HttpRequestLog
 * @date: 2019/4/12  9:41
 */
public class HttpRequestLog {
    public void log(ProceedingJoinPoint joinPoint, String msg) throws Throwable {
        System.out.println("currentTime start:"+System.currentTimeMillis()+"request msg:"+msg);
        final Object proceed = joinPoint.proceed(joinPoint.getArgs());
        System.out.println("currentTime end:"+System.currentTimeMillis()+"response msg:"+proceed);
    }
}
