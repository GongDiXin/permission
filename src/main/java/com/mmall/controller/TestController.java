package com.mmall.controller;

import com.mmall.common.exception.ParamException;
import com.mmall.common.json.JsonData;
import com.mmall.param.TestVo;
import com.mmall.util.BeanValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/1/11 0:18
 * @description
 */
@Controller
@RequestMapping("/test")
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello(){
        logger.info("hello");
        throw new NullPointerException();
        //throw new PermissionException("test permission exception");
        //return JsonData.success("hello permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamException{
        logger.info("validate");
        BeanValidator.validate(vo);
        return JsonData.success("test validate");
    }
}
