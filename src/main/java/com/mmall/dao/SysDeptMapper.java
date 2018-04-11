package com.mmall.dao;

import com.mmall.model.SysDept;

import java.util.List;

/**
 * @author GongDiXin
 * @date 2018/4/8 23:42
 * @param
 * @return
 * @exception
*/
public interface SysDeptMapper {

    /**
     * 根据主键查询部门
     *
     * @author GongDiXin
     * @date 2018/4/10 22:32
     * @param id
     * @return SysDept
    */
    SysDept selectByPrimaryKey(int id);

    /**
     * 新增部门
     *
     * @author GongDiXin
     * @date 2018/4/10 22:49
     * @param dept
     * @return
    */
    int insertSelective(SysDept dept);

    /**
     * 获取所有部门
     *
     * @author GongDiXin
     * @date 2018/4/11 23:09
     * @return List<SysDept>
    */
    List<SysDept> getAllDept();

}