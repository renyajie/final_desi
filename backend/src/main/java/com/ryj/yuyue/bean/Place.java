package com.ryj.yuyue.bean;

import javax.validation.constraints.NotNull;

public class Place {

	private Integer id;

    @NotNull(message="场馆联系电话不能为空")
    private String phone;

    @NotNull(message="场馆名称不能为空")
    private String sName;

    @NotNull(message="场馆地址不能为空")
    private String address;

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

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
    
    @Override
	public String toString() {
		return "Place [id=" + id + ", phone=" + phone + ", sName=" + sName + ", address=" + address + "]";
	}
}