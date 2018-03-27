package com.ryj.yuyue.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户查看课程信息
 * @author Thor
 *
 */
public class ClassInfoResult {
	
	private Integer id;

    private Integer claKId;

    private String claKName;
    
    private Integer pId;
    
    private String pName;

    private Integer teaId;
    
    private String teaName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date cDay;
 
    @JsonFormat(pattern="HH:mm:ss")
    private Date staTime;

    @JsonFormat(pattern="HH:mm:ss")
    private Date endTime;

    private Integer length;

    private Integer allowance;

    private Integer orderNum;

    private Integer expend;
    
    private String property;

	public ClassInfoResult() {
		super();
	}

	public ClassInfoResult(Integer id, Integer claKId, String claKName, Integer pId, String pName, Integer teaId,
			String teaName, Date cDay, Date staTime, Date endTime, Integer length, Integer allowance, Integer orderNum,
			Integer expend, String property) {
		super();
		this.id = id;
		this.claKId = claKId;
		this.claKName = claKName;
		this.pId = pId;
		this.pName = pName;
		this.teaId = teaId;
		this.teaName = teaName;
		this.cDay = cDay;
		this.staTime = staTime;
		this.endTime = endTime;
		this.length = length;
		this.allowance = allowance;
		this.orderNum = orderNum;
		this.expend = expend;
		this.property = property;
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

	public Integer getClaKId() {
		return claKId;
	}

	public void setClaKId(Integer claKId) {
		this.claKId = claKId;
	}

	public String getClaKName() {
		return claKName;
	}

	public void setClaKName(String claKName) {
		this.claKName = claKName;
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

	public Integer getTeaId() {
		return teaId;
	}

	public void setTeaId(Integer teaId) {
		this.teaId = teaId;
	}

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public Date getcDay() {
		return cDay;
	}

	public void setcDay(Date cDay) {
		this.cDay = cDay;
	}

	public Date getStaTime() {
		return staTime;
	}

	public void setStaTime(Date staTime) {
		this.staTime = staTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getAllowance() {
		return allowance;
	}

	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getExpend() {
		return expend;
	}

	public void setExpend(Integer expend) {
		this.expend = expend;
	}

	@Override
	public String toString() {
		return "ClassInfoResult [id=" + id + ", claKId=" + claKId + ", claKName=" + claKName + ", pId=" + pId
				+ ", pName=" + pName + ", teaId=" + teaId + ", teaName=" + teaName + ", cDay=" + cDay + ", staTime="
				+ staTime + ", endTime=" + endTime + ", length=" + length + ", allowance=" + allowance + ", orderNum="
				+ orderNum + ", expend=" + expend + ", property=" + property + "]";
	}
    
    

}
