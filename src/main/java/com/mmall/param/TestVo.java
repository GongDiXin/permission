package com.mmall.param;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/1/14 21:42
 * @description
 */
public class TestVo {

    @NotBlank
    private String msg;

    @NotNull
    private String id;

    @NotEmpty
    private List<String> str;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getStr() {
        return str;
    }

    public void setStr(List<String> str) {
        this.str = str;
    }
}
