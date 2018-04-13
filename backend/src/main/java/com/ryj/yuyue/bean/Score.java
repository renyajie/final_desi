package com.ryj.yuyue.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Score {
    private Integer id;

    @NotNull(message="用户编号不能为空")
    private Integer uId;

    @NotNull(message="课程编号不能为空")
    private Integer claId;

    private Integer claKId;

    @NotNull(message="课程评分不能为空")
    private Integer pId;

    @NotNull(message="手机号不能为空")
    private Float score;

    private String comment;

    private Date scoreTime;

    @NotNull(message="订单编号不能为空")
    private Integer orderId;

    @NotNull(message="年龄不能为空")
    private Integer age;

    @NotNull(message="性别不能为空")
    private String gender;

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
        this.comment = comment == null ? null : comment.trim();
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