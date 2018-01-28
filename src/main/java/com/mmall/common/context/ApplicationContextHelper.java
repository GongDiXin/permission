package com.mmall.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/1/15 22:31
 * @description
 */
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        /**
        *   重写该方法  在spring启动时会把spring的上下文注入到该方法中
        *   再把注入的context赋给我们定义的全局变量applicationContext
        *   @Component("applicationContextHelper") 主要还是通过这个把ApplicationContextHelper交给spring管理
         */
        applicationContext = context;
    }

    public static <T> T popBean(Class<T> clazz){
        if(applicationContext == null){
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    public static <T> T popBean(String name, Class<T> clazz) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(name, clazz);
    }
}
