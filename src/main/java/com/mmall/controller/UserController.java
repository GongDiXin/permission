package com.mmall.controller;

import com.mmall.common.constant.Constant;
import com.mmall.model.SysUser;
import com.mmall.service.SysUserService;
import com.mmall.util.MD5Util;
import com.mmall.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/26 23:26
 */
@Controller
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/login.page")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        SysUser sysUser = sysUserService.findByKeyword(username);
        String errorMessage = "";
        String ret = request.getParameter("ret");
        if (StringUtils.isBlank(username)) {
            errorMessage = "用户名不能为空";
        } else if (StringUtils.isBlank(password)) {
            errorMessage = "密码不能为空";
        } else if (ValidatorUtil.isEmpty(sysUser)) {
            errorMessage = "查询不到指定用户";
        } else if (!sysUser.getPassword().equals(MD5Util.encrypt(password))) {
            errorMessage = "用户名或密码错误";
        } else if (sysUser.getStatus().equals(Constant.STATUS_1)) {
            errorMessage = "用户名已被冻结，请联系管理员";
        } else {
            request.getSession().setAttribute("user",sysUser);
            if (StringUtils.isNotBlank(ret)) {
                // 跳转到上一次页面
                response.sendRedirect(ret);
                return;
            } else {
                // 跳转到主页
                response.sendRedirect("/admin/index.page");
                return;
            }
        }

        request.setAttribute("error", errorMessage);
        request.setAttribute("username", username);
        if (StringUtils.isNotBlank(ret)) {
            request.setAttribute("ret", ret);
        }
        // 注意：如果我们在controller加了访问前缀的话，当我们校验不通过跳转的到signin.jsp的话spring会加上@RequestMapping("/user")即/user/signin.jsp
        // 所以我们要去掉@RequestMapping("/user")
        String path = "signin.jsp";
        // 转发到登录界面 登陆后再跳转到上一次所在页面
        request.getRequestDispatcher(path).forward(request,response);
        return;
    }

    @RequestMapping("/logout.page")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();
        request.getRequestDispatcher("signin.jsp").forward(request,response);
    }
}
