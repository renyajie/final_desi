package bean;

import java.util.Date;

/**
 * 会员卡订单信息
 * Created by Thor on 2018/4/5.
 */

public class CardOrder {

    private Integer id;

    private Integer uId;

    private String uName;

    private Integer cardKId;

    private String cardKName;

    private Date ordTime;

    private Integer cardId;

    private Integer pId;

    private String sName;

    public CardOrder() {
        super();
    }

    public CardOrder(Integer id, Integer uId, String uName, Integer cardKId, String cardKName, Date ordTime,
                           Integer cardId) {
        super();
        this.id = id;
        this.uId = uId;
        this.uName = uName;
        this.cardKId = cardKId;
        this.cardKName = cardKName;
        this.ordTime = ordTime;
        this.cardId = cardId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

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

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Integer getCardKId() {
        return cardKId;
    }

    public void setCardKId(Integer cardKId) {
        this.cardKId = cardKId;
    }

    public String getCardKName() {
        return cardKName;
    }

    public void setCardKName(String cardKName) {
        this.cardKName = cardKName;
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

    @Override
    public String toString() {
        return "CardOrder [id=" + id + ", uId=" + uId + ", uName=" + uName + ", cardKId=" + cardKId
                + ", cardKName=" + cardKName + ", ordTime=" + ordTime + ", cardId=" + cardId + "]";
    }

}
