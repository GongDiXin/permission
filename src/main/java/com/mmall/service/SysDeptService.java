package com.mmall.service;

import com.mmall.common.exception.ParamException;
import com.mmall.dao.SysDeptMapper;
import com.mmall.model.SysDept;
import com.mmall.param.DeptParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.LevelUtil;
import com.mmall.util.ValidatorUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/8 23:38
 */
@Service
public class SysDeptService {

    @Resource
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
        SysDept dept = new SysDept();
        dept.setParentId(deptParam.getParentId());
        dept.setName(deptParam.getName());
        dept.setRemark(deptParam.getRemark());
        dept.setSeq(deptParam.getSeq());
        dept.setLevel(LevelUtil.calculateLevel(getLevel(deptParam.getParentId()), deptParam.getParentId()));
        //TODO:
        dept.setOperator("System");
        dept.setOperateIp("127.0.0.1");

        dept.setOperateTime(new Date());
        sysDeptMapper.insertSelective(dept);
    }

    /**
     * 更新部门
     *
     * @author GongDiXin
     * @date 2018/4/8 23:47
     * @param deptParam
     */
    public void update(DeptParam deptParam) {
        BeanValidator.beanCheck(deptParam);
        if (checkExist(deptParam.getParentId(), deptParam.getName(), deptParam.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept oldDept = sysDeptMapper.selectByPrimaryKey(deptParam.getId());
        if (ValidatorUtil.isEmpty(oldDept)) {
            throw  new ParamException("待更新部门不存在");
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

    /**
     * 获取层级
     *
     * @author GongDiXin
     * @date 2018/4/10 22:35
     * @param deptId
     * @return SysDept
    */
    private String getLevel(int deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }
}
