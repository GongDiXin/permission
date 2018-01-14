package com.mmall.common.json;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/1/11 22:21
 * @description JSON请求结果类
 */
public class JsonData {

    private boolean ret;

    private String msg;

    private Object data;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @author GongDiXin
     * @date 2018/1/11 22:38
     * @description
     * @param result 请求结果
    */
    public JsonData(boolean result){
        this.ret = result;
    }

    /**
     * @author GongDiXin
     * @date 2018/1/11 22:38
     * @description
     * @param msg 返回结果信息
     * @param object 请求结果数据
     * @return jsonData
    */
    public static JsonData success(String msg,Object object){
        JsonData jsonData = new JsonData(true);
        jsonData.msg = msg;
        jsonData.data = object;
        return jsonData;
    }

    /**
     * @author GongDiXin
     * @date 2018/1/11 22:38
     * @description
     * @param object 请求结果
     * @return jsonData
     */
    public static JsonData success(Object object){
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        return jsonData;
    }

    /**
     * @author GongDiXin
     * @date 2018/1/11 22:38
     * @description
     * @return jsonData
     */
    public static JsonData success(){
        JsonData jsonData = new JsonData(true);
        return jsonData;
    }

    /**
     * @author GongDiXin
     * @date 2018/1/11 22:38
     * @description
     * @param msg 返回结果信息
     * @return jsonData
     */
    public static JsonData fail(String msg){
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> result = new HashMap<>();
        result.put("result",ret);
        result.put("msg",msg);
        result.put("data",data);
        return  result;
    }
}
