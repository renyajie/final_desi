package com.ryj.yuyue.bean;

import javax.validation.constraints.NotNull;

public class UserFeature {
    private Integer id;

    @NotNull(message="用户编号不能为空")
    private Integer uId;
    
    @NotNull(message="是否有疾病不能为空")
    private Integer illnese;

    @NotNull(message="是否有手术不能为空")
    private Integer surgery;

    @NotNull(message="是否饮食平衡不能为空")
    private Integer balanceDiet;

    @NotNull(message="是否限制摄入不能为空")
    private Integer limitIntake;

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

    public Integer getIllnese() {
        return illnese;
    }

    public void setIllnese(Integer illnese) {
        this.illnese = illnese;
    }

    public Integer getSurgery() {
        return surgery;
    }

    public void setSurgery(Integer surgery) {
        this.surgery = surgery;
    }

    public Integer getBalanceDiet() {
        return balanceDiet;
    }

    public void setBalanceDiet(Integer balanceDiet) {
        this.balanceDiet = balanceDiet;
    }

    public Integer getLimitIntake() {
        return limitIntake;
    }

    public void setLimitIntake(Integer limitIntake) {
        this.limitIntake = limitIntake;
    }
}