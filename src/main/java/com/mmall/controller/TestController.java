package com.mmall.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/1/11 0:18
 * @description
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        logger.info("hello");
        return "hello permission";
    }
}
