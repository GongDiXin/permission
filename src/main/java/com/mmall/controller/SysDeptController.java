package com.mmall.controller;

import com.mmall.common.json.JsonData;
import com.mmall.dto.DeptLevelDTO;
import com.mmall.param.DeptParam;
import com.mmall.service.SysDeptService;
import com.mmall.service.SysTreeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private SysTreeService treeService;

    @RequestMapping("save.json")
    @ResponseBody
    public JsonData save() {
        DeptParam dept = new DeptParam();
        deptService.save(dept);
        return JsonData.success();
    }

    @RequestMapping("update.json")
    @ResponseBody
    public JsonData update() {
        DeptParam dept = new DeptParam();
        deptService.update(dept);
        return JsonData.success();
    }

    @RequestMapping("tree.json")
    @ResponseBody
    public JsonData tree() {
        List<DeptLevelDTO> dtoList = treeService.deptTree();
        return JsonData.success(dtoList);
    }
}
