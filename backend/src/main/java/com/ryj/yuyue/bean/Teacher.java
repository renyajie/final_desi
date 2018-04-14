package com.ryj.yuyue.bean;

import javax.validation.constraints.NotNull;

public class Teacher {
    private Integer id;

    @NotNull(message="场馆编号不能为空")
    private Integer pId;

    @NotNull(message="教师姓名不能为空")
    private String teaName;

    @NotNull(message="手机号码不能为空")
    private String phone;

    private String intro;

    @NotNull(message="教师年龄不能为空")
    private Integer age;

    @NotNull(message="教师性别不能为空")
    private String gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName == null ? null : teaName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }
}