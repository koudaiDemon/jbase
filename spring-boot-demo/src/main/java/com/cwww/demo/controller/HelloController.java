package com.cwww.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/7/26  10:35
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

}
