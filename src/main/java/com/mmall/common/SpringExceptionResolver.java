package com.mmall.common;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/1/11 22:46
 * @description
 */
public class SpringExceptionResolver implements HandlerExceptionResolver{

    public static final String DEFAULT_MSG = "System Error";

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String url = request.getRequestURI();
        ModelAndView mv;
        //.json .page
        if(url.endsWith(".json")){

        }else {

        }
        return null;
    }
}
