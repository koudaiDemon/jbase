package com.cwww.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/5/15  14:29
 */
@RestController
@RequestMapping(value = "/rest")
public class RestDemoController {

    @RequestMapping(value = "/demo",method = RequestMethod.POST)
    public String rest(@RequestBody Map<String,Object> map){
        return "test";
    }


    @RequestMapping(value = "/demo1",method = RequestMethod.GET)
    public String demo(@RequestParam(name = "code") String code){
        return "test";
    }

    @RequestMapping(value = "/demo2",method = RequestMethod.GET)
    public String demo2(String code){
        System.out.println(code);
        return "test";
    }

    @RequestMapping(value = "/demo3",method = RequestMethod.GET)
    public String demo3(@RequestParam String name, @RequestParam List<String> address) {
        return "demo3";
    }


}
