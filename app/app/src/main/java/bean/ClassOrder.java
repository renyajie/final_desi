package bean;

import java.util.Date;

/**
 * 课程预约信息
 * Created by Thor on 2018/4/5.
 */

public class ClassOrder {
    private Integer id;

    private Integer claId;

    private Integer uId;

    private Integer cardId;

    private Date ordTime;

    private Integer num;

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
}
