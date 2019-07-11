package com.something.beancopy;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;


/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/4/25  13:47
 */
@Slf4j
public class App {

    /**
     * 14:17:02.163 INFO  com.something.beancopy.App 30 apache - Apache Bean Copy Times:10000,spent time:679
     * 14:17:02.374 INFO  com.something.beancopy.App 42 spring - Spring Bean Copy Times:10000,spent time:203
     * 14:17:02.535 INFO  com.something.beancopy.App 55 cglib - Cglib Bean Copier Times:10000,spent time:160
     * 14:17:02.649 INFO  com.something.beancopy.App 68 springCglib - SpringCglib Bean Copier Times:10000,spent time:113
     */

    /**
     * 14:17:49.827 INFO  com.something.beancopy.App 37 apache - Apache Bean Copy Times:100000,spent time:1763
     * 14:17:50.514 INFO  com.something.beancopy.App 49 spring - Spring Bean Copy Times:100000,spent time:682
     * 14:17:50.860 INFO  com.something.beancopy.App 62 cglib - Cglib Bean Copier Times:100000,spent time:345
     * 14:17:51.195 INFO  com.something.beancopy.App 75 springCglib - SpringCglib Bean Copier Times:100000,spent time:335
     */

    private static final Integer TIMES = 100000;

    public static void apache() throws Exception{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0 ; i < TIMES ; i++) {
            Person from = new Person("person","123456",10);
            User to = new User();
            BeanUtils.copyProperties(to,from);
        }
        stopWatch.stop();
        log.info("Apache Bean Copy Times:{},spent time:{}",TIMES,stopWatch.getTime());
    }

    public static void spring(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0 ; i < TIMES ; i++) {
            Person from = new Person("person","123456",10);
            User to = new User();
            org.springframework.beans.BeanUtils.copyProperties(from,to);
        }
        stopWatch.stop();
        log.info("Spring Bean Copy Times:{},spent time:{}",TIMES,stopWatch.getTime());
    }

    public static void cglib(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        BeanCopier beanCopier = BeanCopier.create(Person.class,User.class,false);
        for (int i = 0 ; i < TIMES ; i++) {
            Person from = new Person("person","123456",10);
            User to = new User();
            beanCopier.copy(from,to,null);
        }
        stopWatch.stop();
        log.info("Cglib Bean Copier Times:{},spent time:{}",TIMES,stopWatch.getTime());
    }

    public static void springCglib(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        org.springframework.cglib.beans.BeanCopier beanCopier = org.springframework.cglib.beans.BeanCopier.create(Person.class,User.class,false);
        for (int i = 0 ; i < TIMES ; i++) {
            Person from = new Person("person","123456",10);
            User to = new User();
            beanCopier.copy(from,to,null);
        }
        stopWatch.stop();
        log.info("SpringCglib Bean Copier Times:{},spent time:{}",TIMES,stopWatch.getTime());
    }

    public static void main(String[] args) throws Exception {
        apache();
        spring();
        cglib();
        springCglib();
    }

}
