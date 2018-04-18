package com.ryj.yuyue.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 查询用户评分
 * @author Thor
 *
 */
public class ScoreResult {

	private Integer id;

    private Integer uId;
    
    private String uName;

    private Integer claId;
    
    private String classKName;

    private Integer claKId;

    private Integer pId;
    
    private String pName;

    private Float score;

    private String comment;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date scoreTime;
    
    private Integer orderId;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    
    private Integer age;

    private String gender;
    
    private String picUrl;
    
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public Integer getClaId() {
		return claId;
	}

	public void setClaId(Integer claId) {
		this.claId = claId;
	}

	public String getClassKName() {
		return classKName;
	}

	public void setClassKName(String classKName) {
		this.classKName = classKName;
	}

	public Integer getClaKId() {
		return claKId;
	}

	public void setClaKId(Integer claKId) {
		this.claKId = claKId;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getScoreTime() {
		return scoreTime;
	}

	public void setScoreTime(Date scoreTime) {
		this.scoreTime = scoreTime;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
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
		this.gender = gender;
	}
	
	
    
    
}
