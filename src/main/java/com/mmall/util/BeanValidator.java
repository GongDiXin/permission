package com.mmall.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mmall.common.exception.ParamException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * 参数校验类
 *
 * @author GongDiXin
 * @version 1.0
 * @created 2018/1/14 17:43
 * @description
 */
public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    
    /**
     * @author GongDiXin
     * @date 2018/1/14 21:19
     * @description
     * @param t
     * @param groups
     * @return 错误信息
     * @description 对单个对象进行校验 <T>必须加，作用是指定后面的T，作为泛型
    */
    public static <T> Map<String,String> validate(T t,Class... groups) {
        //自动补全方法返回值 alt+shift+L
        Validator validator = validatorFactory.getValidator();
        Set validateResult = validator.validate(t,groups);
        if(validateResult.isEmpty()){
            return Collections.emptyMap();
        }else {
            LinkedHashMap error = Maps.newLinkedHashMap();
            Iterator iterator = validateResult.iterator();
            while (iterator.hasNext()){
                ConstraintViolation violation = (ConstraintViolation)iterator.next();
                error.put(violation.getPropertyPath().toString(),violation.getMessage());
            }
            return error;
        }
    }

    /**
     * @author GongDiXin
     * @date 2018/1/14 21:22
     * @description
     * @param collection
     * @return 错误信息
     * @description 对集合进行校验 ?表示可以传递任意类型的对象
     */
    public static Map<String,String> validateList(Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map errors;
        do {
            if (!iterator.hasNext()) {
                return Collections.emptyMap();
            }
            errors = validate(iterator.next());
        } while (errors.isEmpty());
        return errors;
    }


    /**
     * @author GongDiXin
     * @date 2018/1/14 23:22
     * @description
     * @param first
     * @return 错误信息
     * @description 统一校验方法
     */
    public static Map<String,String> validateObject(Object first,Object... objects) {
        if(objects != null && objects.length > 0){
            return validateList(Lists.asList(first,objects));
        }else {
            return validate(first,new Class[0]);
        }
    }

    public static void beanCheck(Object param) throws ParamException {
        Map<String,String> errors = validateObject(param);
        if (errors != null) {
            throw new ParamException(errors.toString());
        }
    }
}
