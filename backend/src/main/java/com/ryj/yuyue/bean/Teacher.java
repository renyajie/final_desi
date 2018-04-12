package com.ryj.yuyue.bean;

import javax.validation.constraints.NotNull;

public class Teacher {
    private Integer id;

    @NotNull(message="场馆编号不能为空")
    private Integer pId;

    @NotNull(message="教师姓名不能为空")
    private String teaName;

    @NotNull(message="手机号不能为空")
    private String phone;

    @NotNull(message="教师简介不能为空")
    private String intro;

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

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", pId=" + pId + ", teaName=" + teaName + ", phone=" + phone + ", intro=" + intro
				+ "]";
	}
    
    
}