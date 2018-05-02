package com.mmall.common.filter;

import com.mmall.common.requestholder.RequestHolder;
import com.mmall.model.SysUser;
import com.mmall.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/5/1 23:19
 */
public class LoginFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse)response;
        SysUser user = (SysUser)req.getSession().getAttribute("user");
        if (ValidatorUtil.isEmpty(user)) {
            resp.sendRedirect("/signin.jsp");
            logger.info("用户未登录，跳转至登录界面");
            return;
        }
        RequestHolder.add(user);
        RequestHolder.add(req);
        filter.doFilter(request, response);
        return;
    }

    @Override
    public void destroy() {

    }
}
