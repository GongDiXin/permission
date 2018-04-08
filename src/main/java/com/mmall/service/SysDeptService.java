package com.mmall.service;

import com.mmall.common.exception.ParamException;
import com.mmall.dao.SysDeptMapper;
import com.mmall.param.DeptParam;
import com.mmall.util.BeanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/8 23:38
 */
@Service
public class SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    /**
     * 新增部门
     *
     * @author GongDiXin
     * @date 2018/4/8 23:47
     * @param deptParam
    */
    public void save(DeptParam deptParam) {
        BeanValidator.beanCheck(deptParam);
        if (checkExist(deptParam.getParentId(), deptParam.getName(), deptParam.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }

    }

    /**
     * 判断同一层级下是否存在相同名称的部门
     *
     * @author GongDiXin
     * @date 2018/4/8 23:54
     * @param paramId
     * @param deptName
     * @param deptId
     * @return boolean
    */
    public boolean checkExist(Integer paramId, String deptName, Integer deptId) {
        // TODO:
        return true;
    }
}
