package com.mmall.service;

import com.mmall.dao.SysDeptMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/11 23:06
 */
@Service
public class SysTreeService {

    @Resource
    private SysDeptMapper deptMapper;

}
