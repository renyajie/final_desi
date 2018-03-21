package com.ryj.yuyue.bean;

public class CardKind {
    private Integer id;

    private Integer pId;

    private String cardKName;

    private Integer capacity;

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