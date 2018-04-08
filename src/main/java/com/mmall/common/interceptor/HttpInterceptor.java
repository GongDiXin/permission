package com.mmall.common.interceptor;

import com.mmall.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/1/15 23:14
 * @description
 */
public class HttpInterceptor extends HandlerInterceptorAdapter{

    private Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);

    private static final String START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        logger.info("url : {} , params : {}",url, JsonMapper.obj2String(request.getParameterMap()));
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME,start);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * afterCompletion任何情况下都会执行
     * postHandle正常情况下才会执行
     *
     * @author GongDiXin
     * @date 2018/4/8 22:14
     * @param request
     * @param response
     * @param handler
     * @param ex
    */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI().toString();
        long start = (Long)request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        logger.info("request completed. url:{}, cost:{}", url, end - start);
    }
}
