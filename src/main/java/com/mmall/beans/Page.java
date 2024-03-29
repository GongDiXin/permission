package com.mmall.beans;

import javax.validation.constraints.Min;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/5/1 22:24
 */
public class Page {

    @Min(value = 1, message = "当前页码不合法")
    private int pageNo = 1;

    @Min(value = 1, message = "每页展示数量不合法")
    private int pageSize = 10;

    private int offset;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }

}
