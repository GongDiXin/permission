package com.mmall.service;

import com.mmall.common.exception.ParamException;
import com.mmall.common.requestholder.RequestHolder;
import com.mmall.dao.SysDeptMapper;
import com.mmall.model.SysDept;
import com.mmall.param.DeptParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.IpUtil;
import com.mmall.util.LevelUtil;
import com.mmall.util.ValidatorUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        dept.setOperator(RequestHolder.getCurrentUser().getUsername());
        dept.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
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

        SysDept after = new SysDept();
        after.setId(deptParam.getId());
        after.setParentId(deptParam.getParentId());
        after.setName(deptParam.getName());
        after.setRemark(deptParam.getRemark());
        after.setSeq(deptParam.getSeq());
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());
        after.setLevel(LevelUtil.calculateLevel(getLevel(deptParam.getParentId()),deptParam.getParentId()));
        updateWithChild(oldDept, after);
    }

    /**
     * @author GongDiXin
     * @date 2018/4/16 23:12
     * @param
     * @return
     * @exception
    */
    @Transactional
    private void updateWithChild(SysDept before, SysDept after) {
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if (!newLevelPrefix.equals(oldLevelPrefix)) {
            List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(oldLevelPrefix);
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (SysDept dept : deptList) {
                    String level = dept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKey(after);
    }


    /**
     * 判断同一层级下是否存在相同名称的部门
     *
     * @author GongDiXin
     * @date 2018/4/8 23:54
     * @param parentId
     * @param deptName
     * @param deptId
     * @return boolean
    */
    public boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        return sysDeptMapper.countByNameAndParentId(parentId, deptName, deptId) > 0;
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
