package com.ryj.yuyue.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class ClassInfo {
    private Integer id;
    
    @NotNull(message="课程种类不能为空")
    private Integer claKId;

    @NotNull(message="场馆编号不能为空")
    private Integer pId;

    @NotNull(message="教师编号不能为空")
    private Integer teaId;

    @NotNull(message="上课时间不能为空")
    private Date cDay;

    @NotNull(message="开始时间不能为空")
    private Date staTime;

    @NotNull(message="结束时间不能为空")
    private Date endTime;

    private Integer length;

    private Integer allowance;

    private Integer orderNum;

    @NotNull(message="花费不能为空")
    private Integer expend;

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

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
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
}