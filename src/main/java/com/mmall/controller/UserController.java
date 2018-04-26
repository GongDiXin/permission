package com.mmall.controller;

import com.mmall.common.Constant;
import com.mmall.model.SysUser;
import com.mmall.service.SysUserService;
import com.mmall.util.MD5Util;
import com.mmall.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/login.page")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        SysUser sysUser = sysUserService.findByKeyword(username);
        String errorMessage = "";
        String ret = request.getRequestURI();
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
            } else {
                // 跳转到主页
                response.sendRedirect("/admin/index.page");
            }
        }

        request.setAttribute("error", errorMessage);
        request.setAttribute("username", username);
        if (StringUtils.isNotBlank(ret)) {
            request.setAttribute("ret", ret);
        }
        String path = "signin.jsp";
        // 转发到登录界面 登陆后再跳转到上一次所在页面
        request.getRequestDispatcher(path).forward(request,response);
    }
}
