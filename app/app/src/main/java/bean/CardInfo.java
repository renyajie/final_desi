package bean;

/**
 * 用户的会员卡信息
 * Created by Thor on 2018/4/5.
 */

public class CardInfo {
    private Integer id;

    private Integer cardKId;

    private String cardKName;

    private Integer uId;

    private String uName;

    private Integer allowance;

    public CardInfo() {
        super();
    }

    public CardInfo(Integer id, Integer cardKId, String cardKName, Integer uId, String uName, Integer allowance) {
        super();
        this.id = id;
        this.cardKId = cardKId;
        this.cardKName = cardKName;
        this.uId = uId;
        this.uName = uName;
        this.allowance = allowance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAllowance() {
        return allowance;
    }

    public void setAllowance(Integer allowance) {
        this.allowance = allowance;
    }

    @Override
    public String toString() {
        return "CardInfo [id=" + id + ", cardKId=" + cardKId + ", cardKName=" + cardKName + ", uId=" + uId
                + ", uName=" + uName + ", allowance=" + allowance + "]";
    }
}