package com.mmall.dao;

import com.mmall.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     *
     * @author GongDiXin
     * @date 2018/4/26 22:42
     * @param record
     * @return
     * @exception
    */
    int insert(SysUser record);

    /**
     * 新增
     *
     * @author GongDiXin
     * @date 2018/4/26 22:42
     * @param record
     * @return
     * @exception
     */
    int insertSelective(SysUser record);

    /**
     * 查询用户
     *
     * @author GongDiXin
     * @date 2018/4/26 22:42
     * @param id
     * @return
     * @exception
    */
    SysUser selectByPrimaryKey(Integer id);

    /**
     * 更新
     *
     * @author GongDiXin
     * @date 2018/4/26 22:43
     * @param record
     * @return
     * @exception
    */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * 更新
     *
     * @author GongDiXin
     * @date 2018/4/26 22:43
     * @param record
     * @return
     * @exception
     */
    int updateByPrimaryKey(SysUser record);

    SysUser findByKeyword(@Param("keyword") String keyword);

    int countByMail(@Param("mail") String mail, @Param("id") Integer id);

    int countByTelephone(@Param("telephone") String telephone, @Param("id") Integer id);

    int countByDeptId(@Param("deptId") int deptId);

    List<SysUser> getByIdList(@Param("idList") List<Integer> idList);

    /**
     * 获取所有用户
     *
     * @author GongDiXin
     * @date 2018/4/26 22:45
     * @param
     * @return
     * @exception
    */
    List<SysUser> getAll();
}