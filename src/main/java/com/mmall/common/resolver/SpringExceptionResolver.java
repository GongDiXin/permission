package com.mmall.common.resolver;

import com.mmall.common.exception.ParamException;
import com.mmall.common.exception.PermissionException;
import com.mmall.common.json.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 1.请求包括两种请求 1.界面请求 2.json请求
 * 2.需要为请求定义期望的结果 判断请求正常处理还是异常处理
 * 3.要求：1.项目中所有请求json数据，都使用.json结尾
 *      2.项目中所有请求page页面，都使用.page结尾
 *
 * 4.可利用HandlerExceptionResolver捕获所有异常（已检查异常、未检查异常）
 * 5.新的的HandlerExceptionResolver实现类只需在配置文件中定义即可，可以配置优先级。
 * 6.一种自定义异常对应一种异常处理方法
 * 7.DispatcherServlet初始化HandlerExceptionResolver的时候会自动寻找容器中实现了HandlerExceptionResolver接口的类，
 *   然后添加进来。配置Spring支持异常捕获：
 *   <bean class="com.mmall.common.resolver.SpringExceptionResolver"/>
 * 8.HandlerExceptionResolver和web.xml中配置的error-page会有冲突问题
 *   web.xml中配置error-page同样是配置出现错误时显示的页面：
 *      <error-page>
 *          <error-code>500</error-code>
 *          <location>/500.jsp</location>
 *      </error-page>
 *   如果resolveException返回了ModelAndView，会优先根据返回值中的页面来显示。
 *   不过，resolveException可以返回null，此时则展示web.xml中的error-page的500状态码配置的页面。
 * */

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/1/11 22:46
 */
public class SpringExceptionResolver implements HandlerExceptionResolver{

    Logger logger = LoggerFactory.getLogger(SpringExceptionResolver.class);

    public static final String DEFAULT_MSG = "System Error";

    public static final String SUFFIX_JSON = ".json";
    public static final String SUFFIX_PAGE = ".page";

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String url = request.getRequestURI();
        ModelAndView mv;
        if(url.endsWith(SUFFIX_JSON)){
            if(ex instanceof PermissionException || ex instanceof ParamException){
                JsonData result = JsonData.fail(ex.getMessage());
                mv = new ModelAndView("jsonView",result.toMap());
            }else {
                logger.error("Unknown json Exception,url:" + url,ex);
                JsonData result = JsonData.fail(DEFAULT_MSG);
                mv = new ModelAndView("jsonView",result.toMap());
            }
        }else if (url.endsWith(SUFFIX_PAGE)){
            logger.error("Unknown json Exception,url:" + url,ex);
            JsonData result = JsonData.fail(DEFAULT_MSG);
            mv = new ModelAndView("exception",result.toMap());
        }else {
            logger.error("Unknown page Exception,url:" + url,ex);
            JsonData result = JsonData.fail(DEFAULT_MSG);
            mv = new ModelAndView("jsonView",result.toMap());
        }
        return mv;
    }
}
