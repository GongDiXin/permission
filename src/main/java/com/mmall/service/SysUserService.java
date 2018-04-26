package com.mmall.service;

import com.mmall.common.exception.ParamException;
import com.mmall.dao.SysUserMapper;
import com.mmall.model.SysUser;
import com.mmall.param.UserParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.MD5Util;
import com.mmall.util.PasswordUtil;
import com.mmall.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/26 22:33
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 新增用户
     *
     * @author GongDiXin
     * @date 2018/4/26 23:11
     * @param userParam
     * @exception
    */
    public void save(UserParam userParam) {
        BeanValidator.beanCheck(userParam);
        if (!checkEmailExist(userParam.getId(), userParam.getMail())) {
            throw new ParamException("邮箱已被占用");
        }

        if (!checkTelExist(userParam.getId(), userParam.getTelephone())) {
            throw new ParamException("电话已被占用");
        }

        // TODO password = PasswordUtil.randomPassword() 还没发邮件 暂时写一个
        String password = "123456";
        String encryptpassword = MD5Util.encrypt(password);
        // 构建User
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userParam.getUsername());
        sysUser.setDeptId(userParam.getDeptId());
        sysUser.setMail(userParam.getMail());
        sysUser.setPassword(encryptpassword);
        sysUser.setTelephone(userParam.getTelephone());
        sysUser.setRemark(userParam.getRemark());
        sysUser.setStatus(userParam.getStatus());
        sysUser.setOperateIp("127.0.0.1");
        sysUser.setOperator("system");
        sysUser.setOperateTime(new Date());

        // TODO: sendEmail 发送邮件确认密码
        sysUserMapper.insert(sysUser);
    }

    /**
     * 管理员更新用户
     *
     * @author GongDiXin
     * @date 2018/4/26 23:04
     * @param userParam
     * @exception
    */
    public void updateUser(UserParam userParam) {
        BeanValidator.beanCheck(userParam);
        if (!checkEmailExist(userParam.getId(), userParam.getMail())) {
            throw new ParamException("邮箱已被占用");
        }

        if (!checkTelExist(userParam.getId(), userParam.getTelephone())) {
            throw new ParamException("电话已被占用");
        }

        SysUser beforeUser = sysUserMapper.selectByPrimaryKey(userParam.getId());
        if (ValidatorUtil.isEmpty(beforeUser)) {
            throw new ParamException("用户不存在");
        }

        // 构建User
        SysUser sysUser = new SysUser();
        sysUser.setId(userParam.getId());
        sysUser.setUsername(userParam.getUsername());
        sysUser.setDeptId(userParam.getDeptId());
        sysUser.setMail(userParam.getMail());
        sysUser.setTelephone(userParam.getTelephone());
        sysUser.setRemark(userParam.getRemark());
        sysUser.setStatus(userParam.getStatus());
        sysUser.setOperateIp("127.0.0.1");
        sysUser.setOperator("system");
        sysUser.setOperateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * 检查邮箱是否存在
     *
     * @author GongDiXin
     * @date 2018/4/26 23:54
     * @param userId
     * @param email
     * @return boolean
     */
    public boolean checkEmailExist(Integer userId, String email) {
        return true;
    }

    /**
     * 检查电话是否存在
     *
     * @author GongDiXin
     * @date 2018/4/26 23:54
     * @param userId
     * @param telephone
     * @return boolean
     */
    public boolean checkTelExist(Integer userId, String telephone) {
        return true;
    }

    /**
     * 登录前检查
     *
     * @author GongDiXin
     * @date 2018/4/26 23:31
     * @param keyword
     *          关键字：用户名、密码、邮箱、电话
     * @return SysUser
    */
    public SysUser findByKeyword(String keyword) {
        return null;
    }
}
