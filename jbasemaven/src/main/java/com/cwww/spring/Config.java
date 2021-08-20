package com.cwww.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author cWww
 * @Title Config
 * @Description Spring 配置信息类
 * @date: 2019/6/27  14:05
 */
@Configuration
@ComponentScan(value = {"com.cwww.spring.event"})
public class Config {

}
