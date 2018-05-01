package com.mmall.beans;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/5/1 22:24
 */
public class PageResult<T> {

    private List<T> data = Lists.newArrayList();

    private int total = 0;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
