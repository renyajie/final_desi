package bean;

import java.util.Date;

/**
 * 用户评价
 * Created by Thor on 2018/4/9.
 */

public class Score {

    private Integer id;

    private Integer uId;

    private Integer claId;

    private String classKName;

    private Integer claKId;

    private Integer pId;

    private String pName;

    private Float score;

    private String comment;

    private Date scoreTime;

    private Integer orderId;

    private Date orderTime;

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

    public String getClassKName() {
        return classKName;
    }

    public void setClassKName(String classKName) {
        this.classKName = classKName;
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

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
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
        this.comment = comment;
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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}
