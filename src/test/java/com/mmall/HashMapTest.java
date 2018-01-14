package com.mmall;

import com.mmall.common.json.JsonData;

import java.util.Map;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/1/13 13:17
 * @description
 */
public class HashMapTest {
    public static void main(String[] args){
        JsonData jsonData = new JsonData(true);
        Map<String,Object> map = jsonData.toMap();
        System.out.println("result is " +  map.get("ret")+" msg is "+map.get("msg")+" data is "+map.get("data"));
    }
}
