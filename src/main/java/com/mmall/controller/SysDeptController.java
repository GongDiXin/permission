package com.mmall.controller;

import com.mmall.common.json.JsonData;
import com.mmall.param.DeptParam;
import com.mmall.service.SysDeptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/8 23:34
 */
@Controller
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Resource
    private SysDeptService deptService;

    @RequestMapping("save.json")
    @ResponseBody
    public JsonData save() {
        DeptParam dept = new DeptParam();
        deptService.save(dept);
        return JsonData.success();
    }
}
