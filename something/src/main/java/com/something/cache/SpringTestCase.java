package com.something.cache;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ShiYuying
 * @Title
 * @Description
 * @date 2018/8/31 11:05
 */
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})  //加载配置文件
@RunWith(SpringJUnit4ClassRunner.class)   //使用junit 4进行测试
public class SpringTestCase extends AbstractJUnit4SpringContextTests {

}