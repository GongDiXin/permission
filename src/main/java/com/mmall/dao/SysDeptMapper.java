package com.mmall.dao;

import com.mmall.model.SysDept;
import org.apache.ibatis.annotations.Param;

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
    SysDept selectByPrimaryKey(@Param("id") int id);

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


    /**
     * 更新部门
     *
     * @author GongDiXin
     * @date 2018/4/16 23:22
     * @param dept
     *          待更新部门
     * @return int
    */
    int updateByPrimaryKey(SysDept dept);

    /**
     * 获取当前层级下的所有子部门
     *
     * @author GongDiXin
     * @date 2018/4/16 23:28
     * @param level
     *          层级
     * @return List<SysDept>
    */
    List<SysDept> getChildDeptListByLevel(@Param("level") String level);

    /**
     * 批量更新部门
     *
     * @author GongDiXin
     * @date 2018/4/16 23:42
     * @param sysDeptList
    */
    void batchUpdateLevel(@Param("sysDeptList") List<SysDept> sysDeptList);

    /**
     * 判断部门是否存在
     *
     * @author GongDiXin
     * @date 2018/4/16 23:52
     * @param parentId
     * @param name
     * @param id
     * @return int
    */
    int countByNameAndParentId(@Param("parentId") Integer parentId, @Param("name") String name, @Param("id") Integer id);

}