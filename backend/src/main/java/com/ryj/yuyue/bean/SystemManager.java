package com.ryj.yuyue.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SystemManager {
    private Integer id;

    @Pattern(regexp="^[0-9]{11}$", message="手机号为11位阿拉伯数字")
    private String phone;

    @NotNull(message="账号不能为空")
    private String account;

    @NotNull(message="账号不能为空")
    private String passwd;

    @Pattern(regexp="(^[\u2E80-\u9FFF]{2,6})", message="姓名必须是2-6位中文")
    private String sName;

    @NotNull(message="性别不能为空")
    private String gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }
}