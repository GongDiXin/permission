package com.mmall.dao;

import com.mmall.beans.Page;
import com.mmall.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/26 22:42
 */
public interface SysUserMapper {

    /**
     * 删除用户
     *
     * @author GongDiXin
     * @date 2018/5/1 22:38
     * @param id
     * @return int
    */
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

    /**
     * 根据关键字查询(登录用)
     *
     * @author GongDiXin
     * @date 2018/4/27 22:09
     * @param keyword
     * @return SysUser
    */
    SysUser findByKeyword(@Param("keyword") String keyword);

    /**
     * 获取所有用户
     *
     * @author GongDiXin
     * @date 2018/4/26 22:45
     * @return List<SysUser>
    */
    List<SysUser> getAll();

    /**
     * 校验邮箱唯一性
     *
     * @author GongDiXin
     * @date 2018/4/27 22:14
     * @param mail
     * @param id
     * @return int
    */
    int countByMail(@Param("mail") String mail, @Param("id") Integer id);

    /**
     * 校验电话唯一性
     *
     * @author GongDiXin
     * @date 2018/4/27 22:14
     * @param telephone
     * @param id
     * @return int
     */
    int countByTelephone(@Param("telephone") String telephone, @Param("id") Integer id);

    /**
     * 查询部门用户总数
     *
     * @author GongDiXin
     * @date 2018/4/27 22:16
     * @param deptId
     * @return int
    */
    int countByDeptId(@Param("deptId") int deptId);

    /**
     * 分页查询部门用户
     *
     * @author GongDiXin
     * @date 2018/5/1 22:41
     * @param deptId
     * @param page
     * @return List<SysUser>
    */
    List<SysUser> getPageByDeptId(@Param("deptId") int deptId, @Param("page") Page page);
}