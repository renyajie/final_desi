package com.ryj.yuyue.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class User {
    private Integer id;

    @Pattern(regexp="^[0-9]{11}$", message="手机号为11位阿拉伯数字")
    private String phone;

    @NotNull(message="密码不能为空")
    private String passwd;

    @Pattern(regexp="(^[\u2E80-\u9FFF]{2,6})", message="姓名必须是2-6位中文")
    private String uName;

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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }
}