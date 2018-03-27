package com.ryj.yuyue.bean;

/**
 * 查找管理员的详细信息
 * @author Thor
 *
 */
public class ManagerResult {
	
	private Integer id;

    private String phone;

    private String account;

    private String passwd;

    private String mName;
    
    private String gender;

    private Integer pId;

    private String sName;

	public ManagerResult(Integer id, String phone, String account, String passwd, String mName, String gender,
			Integer pId, String sName) {
		super();
		this.id = id;
		this.phone = phone;
		this.account = account;
		this.passwd = passwd;
		this.mName = mName;
		this.gender = gender;
		this.pId = pId;
		this.sName = sName;
	}

	public ManagerResult() {
		super();
	}

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
		this.phone = phone;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	@Override
	public String toString() {
		return "ManagerResult [id=" + id + ", phone=" + phone + ", account=" + account + ", passwd=" + passwd
				+ ", mName=" + mName + ", gender=" + gender + ", pId=" + pId + ", sName=" + sName + "]";
	}
}
