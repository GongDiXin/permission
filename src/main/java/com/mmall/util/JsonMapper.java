package com.mmall.util;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/1/15 21:54
 * @description
 */
public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

    /**
     * objectMapper 初始化配置
     */
    static {
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    /**
     * @author GongDiXin
     * @date 2018/1/15 22:08
     * @description 对象转字符串
     * @param src
     * @return 转换结果字符串
    */
    public static <T> String obj2String(T src){
        if(src == null){
            return null;
        }
        try {
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        }catch (Exception e){
            logger.warn("parse object to string exception, error : {}" + e);
            return null;
        }
    }

    /**
     * @author GongDiXin
     * @date 2018/1/15 22:28
     * @description 字符串转对象
     * @param src
     * @param typeReference
     * @return T
    */
    public static <T> T string2Object(String src, TypeReference<T> typeReference){
        if(src == null || typeReference == null){
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src,typeReference));
        }catch (Exception e){
            logger.warn("parse string to object exception, string : {}, typeReference<T> : {}, error : {}" + src,typeReference.getType(),e);
            return null;
        }
    }
}
