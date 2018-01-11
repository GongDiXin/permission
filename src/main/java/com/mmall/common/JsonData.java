package com.mmall.common;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/1/11 22:21
 * @description JSON请求结果类
 */
public class JsonData {
    private boolean result;

    private String msg;

    private Object data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
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
        this.result = result;
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
     * @param msg 返回结果信息
     * @return jsonData
     */
    public static JsonData success(String msg){
        JsonData jsonData = new JsonData(true);
        jsonData.msg = msg;
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
}
