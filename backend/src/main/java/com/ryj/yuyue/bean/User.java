package com.ryj.yuyue.bean;

import javax.validation.constraints.NotNull;

public class User {
    private Integer id;

    @NotNull(message="手机号不能为空")
    private String phone;

    @NotNull(message="密码不能为空")
    private String passwd;

    @NotNull(message="姓名不能为空")
    private String uName;

    @NotNull(message="性别不能为空")
    private String gender;

    @NotNull(message="年龄不能为空")
    private Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}