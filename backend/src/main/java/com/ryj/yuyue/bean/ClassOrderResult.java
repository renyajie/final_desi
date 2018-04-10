package com.ryj.yuyue.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/*
 * 查询订单
 */
public class ClassOrderResult {

	private Integer id;
	
	private Integer pId;
	
	private String pName;
	
	private Integer claId;
	
	private String claKName;
	
	private Integer uId;
	
	private String uName;
	
	private Integer cardId;
	
	private String cardKName;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date ordTime;
    
    private Integer num;
    
    private String property;
    
    private Integer isScore;

	public ClassOrderResult() {
		super();
	}

	public ClassOrderResult(Integer id, Integer pId, String pName, Integer claId, String claKName, Integer uId,
			String uName, Integer cardId, String cardKName, Date ordTime, Integer num, Integer isScore) {
		super();
		this.id = id;
		this.pId = pId;
		this.pName = pName;
		this.claId = claId;
		this.claKName = claKName;
		this.uId = uId;
		this.uName = uName;
		this.cardId = cardId;
		this.cardKName = cardKName;
		this.ordTime = ordTime;
		this.num = num;
		this.isScore = isScore;
	}
	
	public Integer getIsScore() {
		return isScore;
	}

	public void setIsScore(Integer isScore) {
		this.isScore = isScore;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

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

	
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Integer getClaId() {
		return claId;
	}

	public void setClaId(Integer claId) {
		this.claId = claId;
	}

	public String getClaKName() {
		return claKName;
	}

	public void setClaKName(String claKName) {
		this.claKName = claKName;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getCardKName() {
		return cardKName;
	}

	public void setCardKName(String cardKName) {
		this.cardKName = cardKName;
	}

	public Date getOrdTime() {
		return ordTime;
	}

	public void setOrdTime(Date ordTime) {
		this.ordTime = ordTime;
	}

	@Override
	public String toString() {
		return "ClassOrderResult [id=" + id + ", pId=" + pId + ", pName=" + pName + ", claId=" + claId + ", claKName="
				+ claKName + ", uId=" + uId + ", uName=" + uName + ", cardId=" + cardId + ", cardKName=" + cardKName
				+ ", ordTime=" + ordTime  + ", num=" + num + "]";
	}
    
}
