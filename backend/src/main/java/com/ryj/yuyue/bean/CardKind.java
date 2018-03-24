package com.ryj.yuyue.bean;

import javax.validation.constraints.NotNull;

public class CardKind {
    private Integer id;

    @NotNull(message="场馆编号不能为空")
    private Integer pId;

    @NotNull(message="会员卡名称不能为空")
    private String cardKName;

    @NotNull(message="会员卡容量不能为空")
    private Integer capacity;

    @NotNull(message="费用不能为空")
    private Integer expend;

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

    public String getCardKName() {
        return cardKName;
    }

    public void setCardKName(String cardKName) {
        this.cardKName = cardKName == null ? null : cardKName.trim();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getExpend() {
        return expend;
    }

    public void setExpend(Integer expend) {
        this.expend = expend;
    }
}