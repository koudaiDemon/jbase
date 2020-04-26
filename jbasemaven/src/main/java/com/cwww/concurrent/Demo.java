package com.cwww.concurrent;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.spi.LoggerContext;

import java.util.*;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/4  19:12
 */
public class Demo {

    private static final Logger LOGGER = LogManager.getLogger(Demo.class);



    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        Demo2 demo2 = new Demo2();
        Demo3 demo3 = new Demo3();
//        Demo2 demo2 = new Demo2();
//        LoggerRepository loggerRepository = org.apache.log4j.LogManager.getLoggerRepository();
//        Enumeration currentLoggers = loggerRepository.getCurrentLoggers();
//        loggerRepository.exists("");
//        while (currentLoggers.hasMoreElements()) {
//            System.out.println(((Logger)currentLoggers.nextElement()).getName());
//        }

//        org.apache.logging.log4j.core.LoggerContext context = (org.apache.logging.log4j.core.LoggerContext)LogManager.getContext(false);
//
//
//
//        context.getLoggers().forEach(System.out::println);

        new Demo().test().forEach(System.out::println);

    }

    public List<LoggerConfigData> test() {
        org.apache.logging.log4j.core.LoggerContext context = (org.apache.logging.log4j.core.LoggerContext)LogManager.getContext(false);

        Configuration configuration = context.getConfiguration();

        Collection<LoggerConfig> loggerConfigs =  configuration.getLoggers().values();

        List<LoggerConfigData> loggerConfigurations = new ArrayList();
        Iterator var4 = loggerConfigs.iterator();

        while(var4.hasNext()) {
            LoggerConfig loggerConfig = (LoggerConfig)var4.next();
            loggerConfigurations.add(this.mapToConfigData(loggerConfig));
        }

        return new ArrayList(loggerConfigurations);
    }

    private LoggerConfigData mapToConfigData(LoggerConfig loggerConfig) {
        LoggerConfigData configData = new LoggerConfigData();
        configData.setName(this.toPresentationFormat(loggerConfig.getName()));
        configData.setEffectiveLevel(loggerConfig.getLevel());
        if (loggerConfig.getParent() != null) {
            configData.setParentName(this.toPresentationFormat(loggerConfig.getParent().getName()));
        }

        return configData;
    }

    private String toPresentationFormat(String name) {
        return name.equals("") ? "root" : name;
    }

}
