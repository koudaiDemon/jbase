package com.cwww.demo.controller;

import com.cwww.demo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/26  10:35
 */
@RestController
@RequestMapping("/")
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public List<String> hello1(){
        return Collections.singletonList("hello");
    }

    @RequestMapping(value = "/helloWorld")
    public void helloWorld(HttpServletRequest httpServletRequest){

        LOGGER.info("httpServletRequest url:{}",httpServletRequest.getRequestURL());
        LOGGER.info("httpServletRequest map:{}",httpServletRequest.getParameterMap());
        httpServletRequest.getParameterMap().forEach((k,v)->LOGGER.info("key:{},value:{}",k,v));

    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public User user(@RequestBody User user){
        LOGGER.info("user:{}",user);
        return user;
    }

}
