package com.mmall.util;


import javax.validation.Validator;

/**
 * 对象校验工具
 *
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/15 23:15
 */
public class ValidatorUtil {

    private ValidatorUtil() {}

    public static boolean isEmpty(Object obj) {
        if (obj == null || "".equals(obj)) {
            return true;
        }
        return false;
    }
}
