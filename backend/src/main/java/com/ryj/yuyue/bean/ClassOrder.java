package com.ryj.yuyue.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class ClassOrder {
    private Integer id;

    @NotNull(message="课程编号不能为空")
    private Integer claId;

    @NotNull(message="用户编号不能为空")
    private Integer uId;

    @NotNull(message="会员卡编号不能为空")
    private Integer cardId;

    private Date ordTime;

    @NotNull(message="预约人数不能为空")
    private Integer num;

    private Integer isScore;
    
    private Integer expend;
    
    public Integer getExpend() {
		return expend;
	}

	public void setExpend(Integer expend) {
		this.expend = expend;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClaId() {
        return claId;
    }

    public void setClaId(Integer claId) {
        this.claId = claId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Date getOrdTime() {
        return ordTime;
    }

    public void setOrdTime(Date ordTime) {
        this.ordTime = ordTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getIsScore() {
        return isScore;
    }

    public void setIsScore(Integer isScore) {
        this.isScore = isScore;
    }
}