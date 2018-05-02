package com.mmall.service;

import com.mmall.beans.Mail;
import com.mmall.beans.Page;
import com.mmall.beans.PageResult;
import com.mmall.common.constant.Constant;
import com.mmall.common.exception.ParamException;
import com.mmall.common.requestholder.RequestHolder;
import com.mmall.dao.SysUserMapper;
import com.mmall.model.SysUser;
import com.mmall.param.UserParam;
import com.mmall.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        userValidate(userParam);
        String password = PasswordUtil.randomPassword();
        String encryptPassword = MD5Util.encrypt(password);
        // 构建User
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userParam.getUsername());
        sysUser.setDeptId(userParam.getDeptId());
        sysUser.setMail(userParam.getMail());
        sysUser.setPassword(encryptPassword);
        sysUser.setTelephone(userParam.getTelephone());
        sysUser.setRemark(userParam.getRemark());
        sysUser.setStatus(userParam.getStatus());
        sysUser.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysUser.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysUser.setOperateTime(new Date());

        //发送邮件
        Mail mail = new Mail(Constant.MAIL_SUBJECT, Constant.MAIL_MESSAGE + password + Constant.MAIL_TIP, userParam.getMail());
        MailUtil.send(mail);
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
        userValidate(userParam);
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
        sysUser.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysUser.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysUser.setOperateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * 检查邮箱是否存在
     *
     * @author GongDiXin
     * @date 2018/4/26 23:54
     * @param userId
     * @param mail
     * @return boolean
     */
    public boolean checkMailExist(Integer userId, String mail) {
        return sysUserMapper.countByMail(mail, userId) > 0;
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
        return sysUserMapper.countByTelephone(telephone, userId) > 0;
    }

    /**
     * 登录前检查
     *
     * @author GongDiXin
     * @date 2018/4/26 23:31
     * @param keyword
     *          关键字：部门id、邮箱、电话
     * @return SysUser
    */
    public SysUser findByKeyword(String keyword) {
        return sysUserMapper.findByKeyword(keyword);
    }

    /**
     * 分页查询部门下的用户
     *
     * @author GongDiXin
     * @date 2018/5/1 22:33
     * @param deptId
     * @return PageResult<SysUser>
    */
    public PageResult<SysUser> getPageByDeptId(int deptId, Page page) {
        BeanValidator.beanCheck(page);
        int count = 0;
        count = sysUserMapper.countByDeptId(deptId);
        PageResult<SysUser> result = new PageResult<>();
        if (count > 0) {
            List<SysUser> list = sysUserMapper.getPageByDeptId(deptId, page);
            result.setTotal(count);
            result.setData(list);
        }
        return result;
    }

    /**
     * 数据校验及验证
     *
     * @author GongDiXin
     * @date 2018/5/1 22:57
     * @param userParam
    */
    private void userValidate(UserParam userParam) {
        BeanValidator.beanCheck(userParam);
        if (checkMailExist(userParam.getId(), userParam.getMail())) {
            throw new ParamException("邮箱已被占用");
        }

        if (checkTelExist(userParam.getId(), userParam.getTelephone())) {
            throw new ParamException("电话已被占用");
        }
    }
}
