package com.mmall.param;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/26 22:23
 */
public class UserParam {

    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, message = "用户名长度需在20个字符以内")
    private String username;

    @NotBlank(message = "电话名不能为空")
    @Length(max = 13, message = "电话长度需在13个字符以内")
    private String telephone;

    @NotBlank(message = "邮箱名不能为空")
    @Length(min = 5, max = 50, message = "邮箱长度需在20个字符以内")
    private String mail;

    @NotNull(message = "必须提供用户所在部门")
    private Integer deptId;

    @NotNull(message = "必须指定用户状态")
    @Min(value = 0, message = "用户状态不合法")
    @Max(value = 2, message = "用户状态不合法")
    private Integer status;

    @Length(max = 200, message = "备注长度在200个字符以内")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
