package com.mmall.controller;

import com.mmall.beans.Page;
import com.mmall.beans.PageResult;
import com.mmall.common.json.JsonData;
import com.mmall.model.SysUser;
import com.mmall.param.UserParam;
import com.mmall.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/26 22:22
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData save(UserParam user) {
        sysUserService.save(user);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData update(UserParam user) {
        sysUserService.updateUser(user);
        return JsonData.success();
    }

    @RequestMapping("page.json")
    @ResponseBody
    public JsonData page(@RequestParam int deptId, Page page) {
        PageResult<SysUser> result = sysUserService.getPageByDeptId(deptId, page);
        return JsonData.success(result);
    }

}
