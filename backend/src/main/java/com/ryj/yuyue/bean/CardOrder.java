package com.ryj.yuyue.bean;

import java.util.Date;

public class CardOrder {
    private Integer id;

    private Integer uId;

    private Integer cardKId;

    private Date ordTime;

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
}