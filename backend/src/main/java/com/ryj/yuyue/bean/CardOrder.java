package com.ryj.yuyue.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class CardOrder {
    private Integer id;

    @NotNull(message="用户编号不能为空")
    private Integer uId;

    @NotNull(message="卡种编号不能为空")
    private Integer cardKId;

    private Date ordTime;

    private Integer cardId;

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

    public Integer getCardKId() {
        return cardKId;
    }

    public void setCardKId(Integer cardKId) {
        this.cardKId = cardKId;
    }

    public Date getOrdTime() {
        return ordTime;
    }

    public void setOrdTime(Date ordTime) {
        this.ordTime = ordTime;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
}