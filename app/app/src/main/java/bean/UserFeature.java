package bean;

/**
 * 用户特征信息
 * Created by Thor on 2018/4/16.
 */

public class UserFeature {
    private Integer id;

    private Integer uId;

    private Integer illnese;

    private Integer surgery;

    private Integer balanceDiet;

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
