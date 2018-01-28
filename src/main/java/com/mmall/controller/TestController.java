package com.mmall.controller;

import com.mmall.common.context.ApplicationContextHelper;
import com.mmall.common.exception.ParamException;
import com.mmall.common.json.JsonData;
import com.mmall.dao.SysAclModuleMapper;
import com.mmall.model.SysAclModule;
import com.mmall.param.TestVo;
import com.mmall.util.BeanValidator;
import com.mmall.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
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
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule sysAclModule = moduleMapper.selectByPrimaryKey(1);
        logger.info(JsonMapper.obj2String(sysAclModule));
        List<TestVo> list = new ArrayList<>();
        TestVo vo1 = new TestVo();
        vo1.setId("1");
        TestVo vo2 = new TestVo();
        vo2.setId("2");
        list.add(vo1);
        list.add(vo1);
        BeanValidator.validate(vo,list);
        return JsonData.success("test validate");
    }
}
